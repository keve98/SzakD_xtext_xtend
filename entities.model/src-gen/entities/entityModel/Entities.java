/**
 */
package entities.entityModel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entities</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link entities.entityModel.Entities#getDeclarations <em>Declarations</em>}</li>
 * </ul>
 *
 * @see entities.entityModel.EntityModelPackage#getEntities()
 * @model
 * @generated
 */
public interface Entities extends EObject {
	/**
	 * Returns the value of the '<em><b>Declarations</b></em>' containment reference list.
	 * The list contents are of type {@link entities.entityModel.NamedType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declarations</em>' containment reference list.
	 * @see entities.entityModel.EntityModelPackage#getEntities_Declarations()
	 * @model containment="true"
	 * @generated
	 */
	EList<NamedType> getDeclarations();

} // Entities
