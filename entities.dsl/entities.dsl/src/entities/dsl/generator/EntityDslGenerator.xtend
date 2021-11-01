package entities.dsl.generator
import entities.entityModel.Entities
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import entities.generator.EntityGenerator
import entities.entityModel.Entity
import java.util.List
import java.util.ArrayList

/**
* Generates code from your model files on save.
*
* See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#codegeneration
*/



class EntityDslGenerator extends AbstractGenerator {
	
  ArrayList<entities.entityModel.Entity> entities = new ArrayList<entities.entityModel.Entity>();
  
  override void doGenerate(Resource resource, IFileSystemAccess2 fsa,
IGeneratorContext context) {
 if (resource?.contents.size != 0 &&
 resource?.contents?.get(0) instanceof Entities) {
 val model = resource.contents.get(0) as Entities
 val entGen = new EntityGenerator()
 

 
 for(Entity e : model.declarations.filter(Entity)){
 	val path = e.name.toString() + ".java"
 	val controllerPath = e.name.toString() + "Controller.java"
 	val servicePath = e.name.toString() + "Service.java"
 	val repositoryPath = e.name.toString() + "Repository.java"
 	val listEntityPath = "list" + e.name.toString() + ".xhtml";
 	val newEntityPath = "new" + e.name.toString() + ".xhtml";
 	val editEntityPath = "edit" + e.name.toString() + ".xhtml";
 	fsa.generateFile(path, entGen.compileEntity(e))
 	fsa.generateFile(servicePath, entGen.compileService(e))
 	fsa.generateFile(controllerPath, entGen.compileController(e))
 	fsa.generateFile(repositoryPath, entGen.compileRepository(e))
 	fsa.generateFile(listEntityPath, entGen.listEntityFrontend(e));
 	fsa.generateFile(newEntityPath, entGen.newEntityFrontend(e));
 	fsa.generateFile(editEntityPath, entGen.editEntityFrontend(e));
 	
 	
 	entities.add(e);
 }
 
 fsa.generateFile("JSFData.java", entGen.compileFrontend(entities));
 
 }
 }
}