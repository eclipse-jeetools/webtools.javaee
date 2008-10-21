package org.eclipse.jst.j2ee.ejb.internal.operations;

public class RemoteComponentInterfaceTemplate
{
  protected static String nl;
  public static synchronized RemoteComponentInterfaceTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    RemoteComponentInterfaceTemplate result = new RemoteComponentInterfaceTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";";
  protected final String TEXT_3 = NL + NL + "import javax.ejb.EJBObject;" + NL + "" + NL + "public interface ";
  protected final String TEXT_4 = " extends EJBObject {" + NL + "" + NL + "}";
  protected final String TEXT_5 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     CreateSessionBeanTemplateModel model = (CreateSessionBeanTemplateModel) argument; 
    
	if (model.getJavaPackageName() != null && model.getJavaPackageName().length() > 0) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append( model.getJavaPackageName() );
    stringBuffer.append(TEXT_2);
    
	}

    stringBuffer.append(TEXT_3);
    stringBuffer.append( model.getRemoteComponentClassSimpleName() );
    stringBuffer.append(TEXT_4);
    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }
}
