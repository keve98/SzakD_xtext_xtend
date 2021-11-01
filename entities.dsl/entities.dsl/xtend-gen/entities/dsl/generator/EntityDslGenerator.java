package entities.dsl.generator;

import com.google.common.collect.Iterables;
import entities.entityModel.Entities;
import entities.entityModel.Entity;
import entities.generator.EntityGenerator;
import java.util.ArrayList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#codegeneration
 */
@SuppressWarnings("all")
public class EntityDslGenerator extends AbstractGenerator {
  private ArrayList<Entity> entities = new ArrayList<Entity>();
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    boolean _and = false;
    EList<EObject> _contents = null;
    if (resource!=null) {
      _contents=resource.getContents();
    }
    int _size = _contents.size();
    boolean _notEquals = (_size != 0);
    if (!_notEquals) {
      _and = false;
    } else {
      EList<EObject> _contents_1 = null;
      if (resource!=null) {
        _contents_1=resource.getContents();
      }
      EObject _get = null;
      if (_contents_1!=null) {
        _get=_contents_1.get(0);
      }
      _and = (_get instanceof Entities);
    }
    if (_and) {
      EObject _get_1 = resource.getContents().get(0);
      final Entities model = ((Entities) _get_1);
      final EntityGenerator entGen = new EntityGenerator();
      Iterable<Entity> _filter = Iterables.<Entity>filter(model.getDeclarations(), Entity.class);
      for (final Entity e : _filter) {
        {
          String _string = e.getName().toString();
          final String path = (_string + ".java");
          String _string_1 = e.getName().toString();
          final String controllerPath = (_string_1 + "Controller.java");
          String _string_2 = e.getName().toString();
          final String servicePath = (_string_2 + "Service.java");
          String _string_3 = e.getName().toString();
          final String repositoryPath = (_string_3 + "Repository.java");
          String _string_4 = e.getName().toString();
          String _plus = ("list" + _string_4);
          final String listEntityPath = (_plus + ".xhtml");
          String _string_5 = e.getName().toString();
          String _plus_1 = ("new" + _string_5);
          final String newEntityPath = (_plus_1 + ".xhtml");
          String _string_6 = e.getName().toString();
          String _plus_2 = ("edit" + _string_6);
          final String editEntityPath = (_plus_2 + ".xhtml");
          fsa.generateFile(path, entGen.compileEntity(e));
          fsa.generateFile(servicePath, entGen.compileService(e));
          fsa.generateFile(controllerPath, entGen.compileController(e));
          fsa.generateFile(repositoryPath, entGen.compileRepository(e));
          fsa.generateFile(listEntityPath, entGen.listEntityFrontend(e));
          fsa.generateFile(newEntityPath, entGen.newEntityFrontend(e));
          fsa.generateFile(editEntityPath, entGen.editEntityFrontend(e));
          this.entities.add(e);
        }
      }
      fsa.generateFile("JSFData.java", entGen.compileFrontend(this.entities));
    }
  }
}
