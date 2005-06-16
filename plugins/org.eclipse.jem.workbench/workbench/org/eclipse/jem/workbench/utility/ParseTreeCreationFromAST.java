/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ParseTreeCreationFromAST.java,v $
 *  $Revision: 1.13 $  $Date: 2005/06/16 17:46:07 $ 
 */
package org.eclipse.jem.workbench.utility;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.Expression;

import org.eclipse.jem.internal.instantiation.*;
 
/**
 * Create a parse tree from an AST node.
 * @since 1.0.0
 */
public class ParseTreeCreationFromAST extends ASTVisitor {
	protected final Resolver resolver;
	protected PTExpression expression;	// Each visit (or endvisit) will put into expression the result of the visit if it produced an expression.
	
	/**
	 * This is the abstract base class used by ParseTreeCreationFromAST to resolve the types to the appropriate
	 * types (e.g. "String" to "java.lang.String"). 
	 * 
	 * @see org.eclipse.jem.workbench.utility.ParseTreeCreationFromAST
	 * @since 1.0.0
	 */
	public static abstract class Resolver {
		
		/**
		 * Resolve the Name. It can return either a PTName if it is just a classname,
		 * or a PTFieldAccess if it resolves to a PTFieldAccess. The PTFieldAccess should
		 * be complete. e.g we have class like:
		 * <code>
		 * 	package xyz; 
		 * 	public class AClass {
		 * 		public java.awt.Rectangle rect;
		 * 	} 
		 * </code>
		 * Then a Name like <code>AClass.rect.x</code> should resolve to:
		 * 
		 * PTFieldAccess:
		 * 	receiver: 
		 * 		PTFieldAccess
		 * 			receiver: xyz.AClass
		 * 			field: "rect"
		 * 	field: "x"
		 *
		 * Actually it can return any valid expression that has a value (i.e. it cannot be a method invocation with a <code>void</code> return type). 
		 * 
		 * @param name
		 * @return Either a fully-qualified name (as a PTName) or a PTFieldAccess, any other type of expression.
		 * 
		 * @since 1.0.0
		 */
		public abstract PTExpression resolveName(Name name) throws InvalidExpressionException;
		
		/**
		 * Resolve the type. If it is an array type return it in format "type[][]".
		 * 
		 * @param type
		 * @return The type name, including brackets if array type.
		 * 
		 * @since 1.0.0
		 */
		public abstract String resolveType(Type type) throws InvalidExpressionException;
		
		/**
		 * This is for resolving "this" literal. It should either return a PTThisLiteral, if it
		 * can't do resolve, or some PTExpression that can resolve to "this" for evaluation.
		 * 
		 * @return If resolvable, a PTExpression, else a PTThisLiteral if not resolvable.
		 * @throws InvalidExpressionException
		 * 
		 * @since 1.0.0
		 */
		public abstract PTExpression resolveThis() throws InvalidExpressionException;
		
		/**
		 * Resolve the type specified as a Name. It may be a simple name or it may be
		 * a qualified name. This is used when we have Name that we know must be a
		 * type. This is so that there is no confusion with it possibly being a field or variable
		 * that has the same case and spelling as a type name.
		 * @param name
		 * @return the type name.
		 * @throws InvalidExpressionException
		 * 
		 * @since 1.0.0
		 */
		public abstract String resolveType(Name name) throws InvalidExpressionException;
		
		/**
		 * This is used by the resolver if it can't resolve for some reason. This will throw
		 * an invalid expression exception which will be handled by the ParseTreeCreationFromAST.
		 * 
		 * @param msg Message to be put into the exception.
		 * @throws InvalidExpressionException
		 * 
		 * @since 1.0.0
		 */
		protected final void throwInvalidExpressionException(String msg) throws InvalidExpressionException {
			throw new InvalidExpressionException(msg);
		}
	}
	
	/*
	 * When an invalid expression has been found this exception should be thrown. It will
	 * be caught at the top and converted into an InvalidExpression and the rest of the parse tree will be
	 * thrown away. 
	 * 
	 * The message will be a message as to why it is invalid.
	 * 
	 * @since 1.0.0
	 */
	protected static class InvalidExpressionException extends IllegalArgumentException {
		
		/**
		 * Comment for <code>serialVersionUID</code>
		 * 
		 * @since 1.1.0
		 */
		private static final long serialVersionUID = 2429845631915206678L;

		/**
		 * @param s The message to be used in the final invalid expression.
		 * 
		 * @since 1.0.0
		 */
		public InvalidExpressionException(String s) {
			super(s);
		}
	}

	/**
	 * Construct with the given resolver.
	 * 
	 * @param resolver
	 * 
	 * @since 1.0.0
	 */
	public ParseTreeCreationFromAST(Resolver resolver) {
		this.resolver = resolver;
	}

	/**
	 * Process the AST Expression and return a PTExpression. If any part was invalid, then
	 * only an PTInvalidExpression will be returned.
	 * 
	 * @param astExpression
	 * @return The PTExpression.
	 * 
	 * @since 1.0.0
	 */
	public final PTExpression createExpression(Expression astExpression) {
		try {
			return perform(astExpression);
		} catch (InvalidExpressionException e) {
			// Create a msg that is formed of the exception message and the full init string.
			String msg = MessageFormat.format(WorkbenchUtilityMessages.getString("ParseTreeCreationFromAST.0"), new Object[] {e.getLocalizedMessage(), astExpression.toString()}); //$NON-NLS-1$
			PTInvalidExpression exp = InstantiationFactory.eINSTANCE.createPTInvalidExpression();
			exp.setMessage(msg);
			return exp;
		}	
	}
	
	/*
	 * Visit the AST expression and get the ParseTree Expression.
	 * This is used by the individual visits when parsing a tree.
	 * It passes to the top method (createExpression), which can
	 * handle the InvalidExpressionException.
	 * 
	 * If any visit doesn't return an expression, then an invalid
	 * expression exception will be thrown to indicate this. If the
	 * incoming expression is <code>null</code>, then return of <code>null</code> is ok because
	 * this would be for an optional expression which didn't exist.
	 * 
	 * @return The new ParseTree Expression or <code>null</code> if incoming expression was null. 
	 * 
	 * @see createExpression(org.eclipse.jdt.core.dom.Expression)
	 * @exception InvalidExpressionException
	 * @since 1.0.0
	 */
	protected final PTExpression perform(Expression astExpression) {
		if (astExpression != null) {
			expression = null;
			astExpression.accept(this);
			if (expression == null)
				throw new InvalidExpressionException(MessageFormat.format(WorkbenchUtilityMessages.getString("ParseTreeCreationFromAST.ExpressionTooComplicated_EXC_"), new Object[] {astExpression.toString()})); //$NON-NLS-1$
			return expression;
		} else
			return null;	// This is ok. It means an optional expression was being processed and the expression didn't exist.
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.ArrayAccess)
	 */
	public boolean visit(ArrayAccess node) {
		PTArrayAccess aa = InstantiationFactory.eINSTANCE.createPTArrayAccess();
		List indexes = aa.getIndexes();
		Expression arrayExp = node;
		while (arrayExp.getNodeType() == ASTNode.ARRAY_ACCESS) {
			// Visit the index to get the index expression.
			ArrayAccess array = (ArrayAccess) arrayExp; 
			indexes.add(0, perform(array.getIndex()));	// We're trying to create the final expression from inside out, the indexes are created in reverse order.
			arrayExp = array.getArray();
		}
		aa.setArray(perform(arrayExp));	// Final arrayExp is the true expression.
		expression = aa;	// Set the return expression for this visit.
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.ArrayCreation)
	 */
	public boolean visit(ArrayCreation node) {
		PTArrayCreation ac = InstantiationFactory.eINSTANCE.createPTArrayCreation();
		ac.setType(resolver.resolveType(node.getType()));
		List acDims = ac.getDimensions();
		List nDims = node.dimensions();
		int nsize = nDims.size();
		for (int i = 0; i < nsize; i++) {
			acDims.add(perform((Expression) nDims.get(i)));
		}
		ac.setInitializer((PTArrayInitializer) perform(node.getInitializer()));
		expression = ac;
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.ArrayInitializer)
	 */
	public boolean visit(ArrayInitializer node) {
		PTArrayInitializer ai = InstantiationFactory.eINSTANCE.createPTArrayInitializer();
		List exps = node.expressions();
		List aiexps = ai.getExpressions();
		int nexp = exps.size();
		for (int i = 0; i < nexp; i++) {
			aiexps.add(perform((Expression) exps.get(i)));
		}
		expression = ai;
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.Assignment)
	 */
	public boolean visit(Assignment node) {
		return false;	// We can't handle assignment.
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.BooleanLiteral)
	 */
	public boolean visit(BooleanLiteral node) {
		PTBooleanLiteral bl = InstantiationFactory.eINSTANCE.createPTBooleanLiteral();
		bl.setBooleanValue(node.booleanValue());
		expression = bl;
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.CastExpression)
	 */
	public boolean visit(CastExpression node) {
		PTCastExpression ct	= InstantiationFactory.eINSTANCE.createPTCastExpression();
		ct.setType(resolver.resolveType(node.getType()));
		ct.setExpression(perform(node.getExpression()));
		expression = ct;
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.CharacterLiteral)
	 */
	public boolean visit(CharacterLiteral node) {
		PTCharacterLiteral cl = InstantiationFactory.eINSTANCE.createPTCharacterLiteral();
		cl.setEscapedValue(node.getEscapedValue());
		cl.setCharValue(node.charValue());
		expression = cl;
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.ClassInstanceCreation)
	 */
	public boolean visit(ClassInstanceCreation node) {
		if (node.getAnonymousClassDeclaration() != null) { throw new InvalidExpressionException(WorkbenchUtilityMessages
				.getString("ParseTreeCreationFromAST.CannotProcessAnonymousDeclarations_EXC_")); //$NON-NLS-1$
		}
		PTClassInstanceCreation cic = InstantiationFactory.eINSTANCE.createPTClassInstanceCreation();
		// If ast level = 2, then you must use getName, but the name needs to be turned into a type
		// so that it can be resolved. If ast level > 2, then it will return a type to be resolved.
		// Note: can't just use resolve name on the name because if a field and a class were spelled
		// the same then the codegen resolver would return an instance ref to the field instead.
		String type = node.getAST().apiLevel() == AST.JLS2 ? resolver.resolveType(node.getName()) : resolver.resolveType(node.getType());
		if (type == null) {
			type = node.getAST().apiLevel() == AST.JLS2 ? node.getName().getFullyQualifiedName() : node.getType().toString();
		}
		cic.setType(type);
		List args = cic.getArguments();
		List nargs = node.arguments();
		int nsize = nargs.size();
		for (int i = 0; i < nsize; i++) {
			args.add(perform((Expression) nargs.get(i)));
		}
		expression = cic;
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.ConditionalExpression)
	 */
	public boolean visit(ConditionalExpression node) {
		PTConditionalExpression ce = InstantiationFactory.eINSTANCE.createPTConditionalExpression();
		ce.setCondition(perform(node.getExpression()));
		ce.setTrue(perform(node.getThenExpression()));
		ce.setFalse(perform(node.getElseExpression()));
		expression = ce;
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.FieldAccess)
	 */
	public boolean visit(FieldAccess node) {
		PTFieldAccess fa = InstantiationFactory.eINSTANCE.createPTFieldAccess();
		fa.setReceiver(perform(node.getExpression()));
		fa.setField(node.getName().getIdentifier());
		expression = fa;
		return false;
	}

	private static HashMap infixOperToParseOper;
	private final PTInfixOperator getParseInfix(InfixExpression.Operator operator) {
		if (prefixOperToParseOper == null) {
			infixOperToParseOper = new HashMap(5);
			infixOperToParseOper.put(InfixExpression.Operator.AND, PTInfixOperator.AND_LITERAL);
			infixOperToParseOper.put(InfixExpression.Operator.CONDITIONAL_AND, PTInfixOperator.CONDITIONAL_AND_LITERAL);
			infixOperToParseOper.put(InfixExpression.Operator.CONDITIONAL_OR, PTInfixOperator.CONDITIONAL_OR_LITERAL);
			infixOperToParseOper.put(InfixExpression.Operator.DIVIDE, PTInfixOperator.DIVIDE_LITERAL);
			infixOperToParseOper.put(InfixExpression.Operator.EQUALS, PTInfixOperator.EQUALS_LITERAL);
			infixOperToParseOper.put(InfixExpression.Operator.GREATER_EQUALS, PTInfixOperator.GREATER_EQUALS_LITERAL);
			infixOperToParseOper.put(InfixExpression.Operator.GREATER, PTInfixOperator.GREATER_LITERAL);
			infixOperToParseOper.put(InfixExpression.Operator.LEFT_SHIFT, PTInfixOperator.LEFT_SHIFT_LITERAL);
			infixOperToParseOper.put(InfixExpression.Operator.LESS_EQUALS, PTInfixOperator.LESS_EQUALS_LITERAL);
			infixOperToParseOper.put(InfixExpression.Operator.LESS, PTInfixOperator.LESS_LITERAL);
			infixOperToParseOper.put(InfixExpression.Operator.MINUS, PTInfixOperator.MINUS_LITERAL);
			infixOperToParseOper.put(InfixExpression.Operator.NOT_EQUALS, PTInfixOperator.NOT_EQUALS_LITERAL);
			infixOperToParseOper.put(InfixExpression.Operator.OR, PTInfixOperator.OR_LITERAL);
			infixOperToParseOper.put(InfixExpression.Operator.PLUS, PTInfixOperator.PLUS_LITERAL);
			infixOperToParseOper.put(InfixExpression.Operator.REMAINDER, PTInfixOperator.REMAINDER_LITERAL);
			infixOperToParseOper.put(InfixExpression.Operator.RIGHT_SHIFT_SIGNED, PTInfixOperator.RIGHT_SHIFT_SIGNED_LITERAL);
			infixOperToParseOper.put(InfixExpression.Operator.RIGHT_SHIFT_UNSIGNED, PTInfixOperator.RIGHT_SHIFT_UNSIGNED_LITERAL);
			infixOperToParseOper.put(InfixExpression.Operator.TIMES, PTInfixOperator.TIMES_LITERAL);
			infixOperToParseOper.put(InfixExpression.Operator.XOR, PTInfixOperator.XOR_LITERAL);
		}
		return (PTInfixOperator) infixOperToParseOper.get(operator);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.InfixExpression)
	 */
	public boolean visit(InfixExpression node) {
		PTInfixExpression inf = InstantiationFactory.eINSTANCE.createPTInfixExpression();
		inf.setLeftOperand(perform(node.getLeftOperand()));
		PTInfixOperator inoper = getParseInfix(node.getOperator());
		if (inoper == null) {
			// It is not one we can handle.
			throw new InvalidExpressionException(
					MessageFormat.format(WorkbenchUtilityMessages.getString("ParseTreeCreationFromAST.OperatorTooComplicatedToHandle_EXC_"), new Object[] { node.getOperator().toString() })); //$NON-NLS-1$
		}
		inf.setOperator(inoper);
		inf.setRightOperand(perform(node.getRightOperand()));
		List eops = inf.getExtendedOperands();
		List neops = node.extendedOperands();
		int nsize = neops.size();
		for (int i = 0; i < nsize; i++) {
			eops.add(perform((Expression) neops.get(i)));
		}
		expression = inf;
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.InstanceofExpression)
	 */
	public boolean visit(InstanceofExpression node) {
		PTInstanceof inof = InstantiationFactory.eINSTANCE.createPTInstanceof();
		inof.setOperand(perform(node.getLeftOperand()));
		inof.setType(resolver.resolveType(node.getRightOperand()));
		expression = inof;
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.PostfixExpression)
	 */
	public boolean visit(PostfixExpression node) {
		return false;	// We can't handle post fix.
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.MethodInvocation)
	 */
	public boolean visit(MethodInvocation node) {
		PTMethodInvocation mi = InstantiationFactory.eINSTANCE.createPTMethodInvocation();
		mi.setReceiver(perform(node.getExpression()));
		mi.setName(node.getName().getIdentifier());
		List args = mi.getArguments();
		List nargs = node.arguments();
		int nsize = nargs.size();
		for (int i = 0; i < nsize; i++) {
			args.add(perform((Expression) nargs.get(i)));
		}
		expression = mi;
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.NullLiteral)
	 */
	public boolean visit(NullLiteral node) {
		expression = InstantiationFactory.eINSTANCE.createPTNullLiteral();
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.NumberLiteral)
	 */
	public boolean visit(NumberLiteral node) {
		PTNumberLiteral nl = InstantiationFactory.eINSTANCE.createPTNumberLiteral();
		nl.setToken(node.getToken());
		expression = nl;
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.ParenthesizedExpression)
	 */
	public boolean visit(ParenthesizedExpression node) {
		PTParenthesizedExpression pe = InstantiationFactory.eINSTANCE.createPTParenthesizedExpression();
		pe.setExpression(perform(node.getExpression()));
		expression = pe;
		return false;
	}

	private static HashMap prefixOperToParseOper;
	private final PTPrefixOperator getParsePrefix(PrefixExpression.Operator operator) {
		if (prefixOperToParseOper == null) {
			prefixOperToParseOper = new HashMap(5);
			prefixOperToParseOper.put(PrefixExpression.Operator.COMPLEMENT, PTPrefixOperator.COMPLEMENT_LITERAL);
			prefixOperToParseOper.put(PrefixExpression.Operator.MINUS, PTPrefixOperator.MINUS_LITERAL);
			prefixOperToParseOper.put(PrefixExpression.Operator.NOT, PTPrefixOperator.NOT_LITERAL);
			prefixOperToParseOper.put(PrefixExpression.Operator.PLUS, PTPrefixOperator.PLUS_LITERAL);
		}
		return (PTPrefixOperator) prefixOperToParseOper.get(operator);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.PrefixExpression)
	 */
	public boolean visit(PrefixExpression node) {
		if (node.getOperand().getNodeType() == ASTNode.NUMBER_LITERAL) {
			// For number literals we see if it is a "+" or "-" prefix, and if it is, we simply
			// create a PTNumberLiteral with the operator already in it. It is a simplification.
			// Any other operator we've left alone since those won't be decoded simply by the
			// Number decoder.
			// If not a number literal, then leave alone since needs to be handled as a prefix
			// operation.
			PrefixExpression.Operator operator = node.getOperator();
			if (operator == PrefixExpression.Operator.PLUS || operator == PrefixExpression.Operator.MINUS) {
				PTNumberLiteral nm = InstantiationFactory.eINSTANCE.createPTNumberLiteral();
				nm.setToken(operator.toString() + ((NumberLiteral) node.getOperand()).getToken());
				expression = nm;
				return false;
			}
		}

		PTPrefixExpression pe = InstantiationFactory.eINSTANCE.createPTPrefixExpression();
		PTPrefixOperator ptoper = getParsePrefix(node.getOperator());
		if (ptoper == null) {
			// It is not one we can handle.
			throw new InvalidExpressionException(
				MessageFormat.format(WorkbenchUtilityMessages.getString("ParseTreeCreationFromAST.OperatorTooComplicatedToHandle_EXC_"), new Object[] { node.getOperator().toString() })); //$NON-NLS-1$
		}
		pe.setOperator(ptoper);
		expression = pe;
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.QualifiedName)
	 */
	public boolean visit(QualifiedName node) {
		expression = resolver.resolveName(node);
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.SimpleName)
	 */
	public boolean visit(SimpleName node) {
		expression = resolver.resolveName(node);
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.StringLiteral)
	 */
	public boolean visit(StringLiteral node) {
		PTStringLiteral sl = InstantiationFactory.eINSTANCE.createPTStringLiteral();
		sl.setEscapedValue(node.getEscapedValue());
		sl.setLiteralValue(node.getLiteralValue());
		expression = sl;
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.SuperFieldAccess)
	 */
	public boolean visit(SuperFieldAccess node) {
		return false;	// We can't handle post fix.
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.SuperMethodInvocation)
	 */
	public boolean visit(SuperMethodInvocation node) {
		return false;	// We can't handle post fix.
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.ThisExpression)
	 */
	public boolean visit(ThisExpression node) {
		expression = resolver.resolveThis();
		return false;	
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.TypeLiteral)
	 */
	public boolean visit(TypeLiteral node) {
		PTTypeLiteral ptl = InstantiationFactory.eINSTANCE.createPTTypeLiteral();
		ptl.setType(resolver.resolveType(node.getType()));
		expression = ptl;
		return false;
	}

}
