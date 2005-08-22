/******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.common.jdt.internal.classpath;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jst.common.frameworks.CommonFrameworksPlugin;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class ClasspathDecorationsManager
{
    private final File f;
    private final HashMap decorations;
    
    public ClasspathDecorationsManager( final String plugin )
    {
        final IWorkspace ws = ResourcesPlugin.getWorkspace();
        final File wsdir = ws.getRoot().getLocation().toFile();
        final File wsmdroot = new File( wsdir, ".metadata/.plugins" ); //$NON-NLS-1$
        final File pmdroot = new File( wsmdroot, plugin );
    
        this.f = new File( pmdroot, "classpath.decorations.xml" ); //$NON-NLS-1$
        this.decorations = read();
    }
    
    public ClasspathDecorations getDecorations( final String container,
                                                final String entry )
    {
        final HashMap submap = (HashMap) this.decorations.get( container );
        
        if( submap == null )
        {
            return null;
        }
        
        return (ClasspathDecorations) submap.get( entry );
    }

    public void setDecorations( final String container,
                                final String entry,
                                final ClasspathDecorations dec )
    {
        HashMap submap = (HashMap) this.decorations.get( container );
        
        if( submap == null )
        {
            submap = new HashMap();
            this.decorations.put( container, submap );
        }
        
        submap.put( entry, dec );
    }
    
    public void clearAllDecorations( final String container )
    {
        this.decorations.remove( container );
    }

    public void save()
    {
        final File folder = this.f.getParentFile();
        
        if( ! folder.exists() && ! folder.mkdirs() )
        {
            return;
        }
        
        PrintWriter w = null;
        
        try
        {
            w = new PrintWriter( new BufferedWriter( new FileWriter( this.f ) ) );
            
            w.println( "<classpath>" ); //$NON-NLS-1$
            
            for( Iterator itr1 = decorations.entrySet().iterator(); 
                 itr1.hasNext(); )
            {
                final Map.Entry entry1 = (Map.Entry) itr1.next();
                final Map submap = (Map) entry1.getValue();
                
                w.print( "  <container id=\"" ); //$NON-NLS-1$
                w.print( (String) entry1.getKey() );
                w.println( "\">" ); //$NON-NLS-1$
                
                for( Iterator itr2 = submap.entrySet().iterator(); 
                     itr2.hasNext(); )
                {
                    final Map.Entry entry2 = (Map.Entry) itr2.next();
                    
                    final ClasspathDecorations dec 
                        = (ClasspathDecorations) entry2.getValue();
                    
                    w.print( "    <entry id=\"" ); //$NON-NLS-1$
                    w.print( (String) entry2.getKey() );
                    w.println( "\">" ); //$NON-NLS-1$
                    
                    if( dec.getSourceAttachmentPath() != null )
                    {
                        w.print( "      <source-attachment-path>" ); //$NON-NLS-1$
                        w.print( dec.getSourceAttachmentPath().toString() );
                        w.println( "</source-attachment-path>" ); //$NON-NLS-1$
                    }

                    if( dec.getSourceAttachmentRootPath() != null )
                    {
                        w.print( "      <source-attachment-root-path>" ); //$NON-NLS-1$
                        w.print( dec.getSourceAttachmentRootPath().toString() );
                        w.println( "</source-attachment-root-path>" ); //$NON-NLS-1$
                    }
                    
                    final IClasspathAttribute[] attrs 
                        = dec.getExtraAttributes();
                    
                    for( int i = 0; i < attrs.length; i++ )
                    {
                        final IClasspathAttribute attr = attrs[ i ];
                        
                        w.print( "      <attribute name=\"" ); //$NON-NLS-1$
                        w.print( attr.getName() );
                        w.print( "\">" ); //$NON-NLS-1$
                        w.print( attr.getValue() );
                        w.println( "</attribute>" ); //$NON-NLS-1$
                    }
                    
                    w.println( "    </entry>" ); //$NON-NLS-1$
                }
                
                w.println( "  </container>" ); //$NON-NLS-1$
            }
            
            w.println( "</classpath>" ); //$NON-NLS-1$
        }
        catch( IOException e )
        {
            CommonFrameworksPlugin.log( e );
        }
        finally
        {
            w.close();
        }
    }
    
    private HashMap read()
    {
        final HashMap map = new HashMap();
        if( ! this.f.exists() ) return map;

        InputStream in = null;
        Element root = null;

        try
        {
            final DocumentBuilderFactory factory 
                = DocumentBuilderFactory.newInstance();
            
            final DocumentBuilder docbuilder = factory.newDocumentBuilder();
            
            in = new BufferedInputStream( new FileInputStream( f ) );
            root = docbuilder.parse( in ).getDocumentElement();
        }
        catch( Exception e )
        {
            CommonFrameworksPlugin.log( e );
            return map;
        }
        finally
        {
            if( in != null )
            {
                try
                {
                    in.close();
                }
                catch( IOException e ) {}
            }
        }
        
        for( Iterator itr1 = elements( root, "container" ); itr1.hasNext(); ) //$NON-NLS-1$
        {
            final Element e1 = (Element) itr1.next();
            final String cid = e1.getAttribute( "id" ); //$NON-NLS-1$
            
            final HashMap submap = new HashMap();
            map.put( cid, submap );
            
            for( Iterator itr2 = elements( e1, "entry" ); itr2.hasNext(); ) //$NON-NLS-1$
            {
                final Element e2 = (Element) itr2.next();
                final String eid = e2.getAttribute( "id" ); //$NON-NLS-1$
                final ClasspathDecorations dec = new ClasspathDecorations();
                
                submap.put( eid, dec );
                
                for( Iterator itr3 = elements( e2 ); itr3.hasNext(); )
                {
                    final Element e3 = (Element) itr3.next();
                    final String n = e3.getNodeName();
                    
                    if( n.equals( "source-attachment-path" ) ) //$NON-NLS-1$
                    {
                        dec.setSourceAttachmentPath( new Path( text( e3 ) ) );
                    }
                    else if( n.equals( "source-attachment-root-path" ) ) //$NON-NLS-1$
                    {
                        dec.setSourceAttachmentRootPath( new Path( text( e3 ) ) );
                    }
                    else if( n.equals( "attribute" ) ) //$NON-NLS-1$
                    {
                        final String name = e3.getAttribute( "name" ); //$NON-NLS-1$
                        dec.addExtraAttribute( name, text( e3 ) );
                    }
                }
            }
        }
        
        return map;
    }
    
    private static String text( final Element el )
    {
        final NodeList nodes = el.getChildNodes();

        String str = null;
        StringBuffer buf = null;
        
        for( int i = 0, n = nodes.getLength(); i < n; i++ )
        {
            final Node node = nodes.item( i );
            
            if( node.getNodeType() == Node.TEXT_NODE )
            {
                final String val = node.getNodeValue();
                
                if( buf != null )
                {
                    buf.append( val );
                }
                else if( str != null )
                {
                    buf = new StringBuffer();
                    buf.append( str );
                    buf.append( val );
                    
                    str = null;
                }
                else
                {
                    str = val;
                }
            }
        }
        
        if( buf != null )
        {
            return buf.toString();
        }
        return str;
    }
    
    private static Iterator elements( final Element el,
                                     final String name )
    {
        return new ElementsIterator( el, name );
    }
    
    private static Iterator elements( final Element el )
    {
        return new ElementsIterator( el, null );
    }

    private static final class ElementsIterator
    
        implements Iterator
        
    {
        private final NodeList nodes;
        private final int length;
        private final String name;
        private int position;
        private Element element;

        public ElementsIterator( final Element parent, 
                                 final String name )
        {
            this.nodes = parent.getChildNodes();
            this.length = nodes.getLength();
            this.position = -1;
            this.name = name;

            advance();
        }

        private void advance()
        {
            this.element = null;
            this.position++;

            for( ; this.position < this.length && this.element == null; 
                 this.position++ )
            {
                final Node node = this.nodes.item( this.position );

                if( node.getNodeType() == Node.ELEMENT_NODE &&
                    ( this.name == null || 
                      node.getNodeName().equals( this.name ) ) ) 
                {
                    this.element = (Element) node;
                }
            }
        }

        public boolean hasNext() 
        {
            return ( this.element != null );
        }

        public Object next() 
        {
            final Element el = this.element;

            if( el == null ) 
            {
                throw new NoSuchElementException();
            }

            advance();

            return el;
        }

        public void remove() 
        {
            throw new UnsupportedOperationException();
        }
    }
    
}
