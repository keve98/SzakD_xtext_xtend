/**
 */
package entities.entityModel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link entities.entityModel.Entity#getBaseEntity <em>Base Entity</em>}</li>
 *   <li>{@link entities.entityModel.Entity#getFields <em>Fields</em>}</li>
 * </ul>
 *
 * @see entities.entityModel.EntityModelPackage#getEntity()
 * @model
 * @generated
 */
public interface Entity extends NamedType {
	/**
	 * Returns the value of the '<em><b>Base Entity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Entity</em>' reference.
	 * @see #setBaseEntity(Entity)
	 * @see entities.entityModel.EntityModelPackage#getEntity_BaseEntity()
	 * @model
	 * @generated
	 */
	Entity getBaseEntity();

	/**
	 * Sets the value of the '{@link entities.entityModel.Entity#getBaseEntity <em>Base Entity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Entity</em>' reference.
	 * @see #getBaseEntity()
	 * @generated
	 */
	void setBaseEntity(Entity value);

	/**
	 * Returns the value of the '<em><b>Fields</b></em>' containment reference list.
	 * The list contents are of type {@link entities.entityModel.Field}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fields</em>' containment reference list.
	 * @see entities.entityModel.EntityModelPackage#getEntity_Fields()
	 * @model containment="true"
	 * @generated
	 */
	EList<Field> getFields();

} // Entity
