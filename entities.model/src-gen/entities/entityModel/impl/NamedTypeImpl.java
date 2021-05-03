/**
 */
package entities.entityModel.impl;

import entities.entityModel.EntityModelPackage;
import entities.entityModel.NamedType;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Named Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class NamedTypeImpl extends NamedElementImpl implements NamedType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NamedTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EntityModelPackage.Literals.NAMED_TYPE;
	}

} //NamedTypeImpl
