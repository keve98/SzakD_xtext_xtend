package entities.generator;

import entities.entityModel.Entity;
import entities.entityModel.Field;
import javax.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class EntityGenerator {
  @Inject
  @Extension
  private IQualifiedNameProvider _iQualifiedNameProvider;
  
  public CharSequence compileEntity(final Entity e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(" \t\t");
    {
      QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(e.eContainer());
      boolean _tripleNotEquals = (_fullyQualifiedName != null);
      if (_tripleNotEquals) {
        _builder.append("package ");
        QualifiedName _fullyQualifiedName_1 = this._iQualifiedNameProvider.getFullyQualifiedName(e.eContainer());
        _builder.append(_fullyQualifiedName_1, " \t\t");
        _builder.append(";");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append(" \t\t");
    _builder.newLine();
    _builder.append(" \t\t");
    _builder.append("import java.util.*;");
    _builder.newLine();
    _builder.append(" \t\t");
    _builder.append("import javax.persistence.*;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Entity");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public class  ");
    String _name = e.getName();
    _builder.append(_name, "\t");
    _builder.append(" ");
    {
      Entity _baseEntity = e.getBaseEntity();
      boolean _tripleNotEquals_1 = (_baseEntity != null);
      if (_tripleNotEquals_1) {
        _builder.append("extends ");
        String _name_1 = e.getBaseEntity().getName();
        _builder.append(_name_1, "\t");
        _builder.append(" ");
      }
    }
    _builder.append("{");
    _builder.newLineIfNotEmpty();
    {
      EList<Field> _fields = e.getFields();
      for(final Field f : _fields) {
        {
          boolean _isArray = f.isArray();
          if (_isArray) {
            _builder.append("\t");
            _builder.append("private List<");
            QualifiedName _fullyQualifiedName_2 = this._iQualifiedNameProvider.getFullyQualifiedName(f.getDataType());
            _builder.append(_fullyQualifiedName_2, "\t");
            _builder.append("> ");
            String _name_2 = f.getName();
            _builder.append(_name_2, "\t");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t");
            _builder.append("private ");
            QualifiedName _fullyQualifiedName_3 = this._iQualifiedNameProvider.getFullyQualifiedName(f.getDataType());
            _builder.append(_fullyQualifiedName_3, "\t");
            _builder.append(" ");
            String _name_3 = f.getName();
            _builder.append(_name_3, "\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.newLine();
    {
      EList<Field> _fields_1 = e.getFields();
      for(final Field f_1 : _fields_1) {
        {
          boolean _isArray_1 = f_1.isArray();
          if (_isArray_1) {
            _builder.append("\t");
            CharSequence _compileFieldsArray = this.compileFieldsArray(f_1);
            _builder.append(_compileFieldsArray, "\t");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t");
            CharSequence _compileFields = this.compileFields(f_1);
            _builder.append(_compileFields, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence compileFieldsArray(final Field f) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(f.getDataType());
    _builder.append(_fullyQualifiedName);
    _builder.append(" get");
    String _firstUpper = StringExtensions.toFirstUpper(f.getName());
    _builder.append(_firstUpper);
    _builder.append("(){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t");
    _builder.append("return ");
    String _name = f.getName();
    _builder.append(_name, "\t\t\t\t");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence compileFields(final Field f) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(f.getDataType());
    _builder.append(_fullyQualifiedName);
    _builder.append(" get");
    String _firstUpper = StringExtensions.toFirstUpper(f.getName());
    _builder.append(_firstUpper);
    _builder.append("(){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("return ");
    String _name = f.getName();
    _builder.append(_name, "\t");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public void set");
    String _firstUpper_1 = StringExtensions.toFirstUpper(f.getName());
    _builder.append(_firstUpper_1);
    _builder.append("(");
    QualifiedName _fullyQualifiedName_1 = this._iQualifiedNameProvider.getFullyQualifiedName(f.getDataType());
    _builder.append(_fullyQualifiedName_1);
    _builder.append(" ");
    String _name_1 = f.getName();
    _builder.append(_name_1);
    _builder.append("){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("this.");
    String _name_2 = f.getName();
    _builder.append(_name_2, "\t");
    _builder.append(" = ");
    String _name_3 = f.getName();
    _builder.append(_name_3, "\t");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence compileController(final Entity e) {
    StringConcatenation _builder = new StringConcatenation();
    {
      QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(e.eContainer());
      boolean _tripleNotEquals = (_fullyQualifiedName != null);
      if (_tripleNotEquals) {
        _builder.append("package ");
        QualifiedName _fullyQualifiedName_1 = this._iQualifiedNameProvider.getFullyQualifiedName(e.eContainer());
        _builder.append(_fullyQualifiedName_1);
        _builder.append(";");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import com.example.SzakD_Rest.entities.*;");
    _builder.newLine();
    _builder.append("import com.example.SzakD_Rest.services.*;");
    _builder.newLine();
    _builder.append("import org.springframework.web.bind.annotation.*;");
    _builder.newLine();
    _builder.append("import java.util.List;");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RestController");
    _builder.newLine();
    _builder.append("public class ");
    String _name = e.getName();
    _builder.append(_name);
    _builder.append("Controller {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    String _firstUpper = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper, "\t");
    _builder.append("Service ");
    String _lowerCase = e.getName().toLowerCase();
    _builder.append(_lowerCase, "\t");
    _builder.append("Service;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    String _firstUpper_1 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_1, "\t");
    _builder.append("Controller(");
    String _firstUpper_2 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_2, "\t");
    _builder.append("Service ");
    String _lowerCase_1 = e.getName().toLowerCase();
    _builder.append(_lowerCase_1, "\t");
    _builder.append("){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("this.");
    String _firstUpper_3 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_3, "\t\t");
    _builder.append("Service = ");
    String _lowerCase_2 = e.getName().toLowerCase();
    _builder.append(_lowerCase_2, "\t\t");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@GetMapping(\"/");
    String _lowerCase_3 = e.getName().toLowerCase();
    _builder.append(_lowerCase_3, "\t");
    _builder.append("s\")");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("List<");
    String _firstUpper_4 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_4, "\t");
    _builder.append("> all(){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("return ");
    String _lowerCase_4 = e.getName().toLowerCase();
    _builder.append(_lowerCase_4, "\t\t");
    _builder.append("Service.getAll");
    String _firstUpper_5 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_5, "\t\t");
    _builder.append("s();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@GetMapping(\"/");
    String _lowerCase_5 = e.getName().toLowerCase();
    _builder.append(_lowerCase_5, "\t");
    _builder.append("s/{id}\")");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _firstUpper_6 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_6, "\t");
    _builder.append(" findById(@PathVariable Long id){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("return ");
    String _lowerCase_6 = e.getName().toLowerCase();
    _builder.append(_lowerCase_6, "\t\t");
    _builder.append("Service.findById(id);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@PostMapping(\"/");
    String _lowerCase_7 = e.getName().toLowerCase();
    _builder.append(_lowerCase_7, "\t");
    _builder.append("s\")");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _firstUpper_7 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_7, "\t");
    _builder.append(" new");
    String _firstUpper_8 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_8, "\t");
    _builder.append("(@RequestBody ");
    String _firstUpper_9 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_9, "\t");
    _builder.append(" ");
    String _lowerCase_8 = e.getName().toLowerCase();
    _builder.append(_lowerCase_8, "\t");
    _builder.append("){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("return ");
    String _lowerCase_9 = e.getName().toLowerCase();
    _builder.append(_lowerCase_9, "\t\t");
    _builder.append("Service.new");
    String _firstUpper_10 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_10, "\t\t");
    _builder.append("(");
    String _lowerCase_10 = e.getName().toLowerCase();
    _builder.append(_lowerCase_10, "\t\t");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@PutMapping(\"/");
    String _lowerCase_11 = e.getName().toLowerCase();
    _builder.append(_lowerCase_11, "\t");
    _builder.append("s/{id}\")");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _firstUpper_11 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_11, "\t");
    _builder.append(" update");
    String _firstUpper_12 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_12, "\t");
    _builder.append("(@RequestBody ");
    String _firstUpper_13 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_13, "\t");
    _builder.append(" new");
    String _firstUpper_14 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_14, "\t");
    _builder.append(", @RequestParam Long id){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("return ");
    String _lowerCase_12 = e.getName().toLowerCase();
    _builder.append(_lowerCase_12, "\t\t");
    _builder.append("Service.update");
    String _firstUpper_15 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_15, "\t\t");
    _builder.append("(new");
    String _firstUpper_16 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_16, "\t\t");
    _builder.append(", id);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@DeleteMapping(\"/");
    String _lowerCase_13 = e.getName().toLowerCase();
    _builder.append(_lowerCase_13, "\t");
    _builder.append("s/{id}\")");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("void delete");
    String _firstUpper_17 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_17, "\t");
    _builder.append("(@PathVariable Long id){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    String _lowerCase_14 = e.getName().toLowerCase();
    _builder.append(_lowerCase_14, "\t\t");
    _builder.append("Service.delete");
    String _firstUpper_18 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_18, "\t\t");
    _builder.append("(id);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence compileService(final Entity e) {
    StringConcatenation _builder = new StringConcatenation();
    {
      QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(e.eContainer());
      boolean _tripleNotEquals = (_fullyQualifiedName != null);
      if (_tripleNotEquals) {
        _builder.append("package ");
        QualifiedName _fullyQualifiedName_1 = this._iQualifiedNameProvider.getFullyQualifiedName(e.eContainer());
        _builder.append(_fullyQualifiedName_1);
        _builder.append(";");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.example.SzakD_Rest.entities.*;");
    _builder.newLine();
    _builder.append("import com.example.SzakD_Rest.exceptions.NotFoundEntityException;");
    _builder.newLine();
    _builder.append("import com.example.SzakD_Rest.repositories.*;");
    _builder.newLine();
    _builder.append("import org.springframework.beans.factory.annotation.Autowired;");
    _builder.newLine();
    _builder.append("import org.springframework.stereotype.Service;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import java.util.List;");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("@Service");
    _builder.newLine();
    _builder.append("public class ");
    String _firstUpper = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper);
    _builder.append("Service {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("private final ");
    String _firstUpper_1 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_1, "    ");
    _builder.append("Repository blogRepository;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("@Autowired");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public ");
    String _firstUpper_2 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_2, "    ");
    _builder.append("Service(");
    String _firstUpper_3 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_3, "    ");
    _builder.append("Repository r){this.blogRepository = r;}");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public List<");
    String _firstUpper_4 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_4, "    ");
    _builder.append("> getAll");
    String _firstUpper_5 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_5, "    ");
    _builder.append("s(){return blogRepository.findAll();}");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public ");
    String _firstUpper_6 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_6, "    ");
    _builder.append(" new");
    String _firstUpper_7 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_7, "    ");
    _builder.append("(");
    String _firstUpper_8 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_8, "    ");
    _builder.append(" b){return blogRepository.save(b);}");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public ");
    String _firstUpper_9 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_9, "    ");
    _builder.append(" findById(Long id){");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("return blogRepository.findById(id)");
    _builder.newLine();
    _builder.append("                ");
    _builder.append(".orElseThrow(()->new NotFoundEntityException(id));");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public void delete");
    String _firstUpper_10 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_10, "    ");
    _builder.append("(Long id){");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("blogRepository.deleteById(id);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public ");
    String _firstUpper_11 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_11, "    ");
    _builder.append("update ");
    String _firstUpper_12 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_12, "    ");
    _builder.append("(");
    String _firstUpper_13 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_13, "    ");
    _builder.append(" new");
    String _firstUpper_14 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_14, "    ");
    _builder.append(", Long id){");
    _builder.newLineIfNotEmpty();
    _builder.append("       ");
    _builder.newLine();
    _builder.append("       ");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return ");
    String _lowerCase = e.getName().toLowerCase();
    _builder.append(_lowerCase, "        ");
    _builder.append("Repository.findById(id)");
    _builder.newLineIfNotEmpty();
    _builder.append("                ");
    _builder.append(".map(");
    String _lowerCase_1 = e.getName().toLowerCase();
    _builder.append(_lowerCase_1, "                ");
    _builder.append(" -> {");
    _builder.newLineIfNotEmpty();
    {
      EList<Field> _fields = e.getFields();
      for(final Field f : _fields) {
        {
          boolean _isArray = f.isArray();
          if (_isArray) {
            _builder.append("                   ");
            String _lowerCase_2 = e.getName().toLowerCase();
            _builder.append(_lowerCase_2, "                   ");
            _builder.append(".get");
            String _firstUpper_15 = StringExtensions.toFirstUpper(f.getName());
            _builder.append(_firstUpper_15, "                   ");
            _builder.append("().clear();");
            _builder.newLineIfNotEmpty();
            _builder.append("                   ");
            String _lowerCase_3 = e.getName().toLowerCase();
            _builder.append(_lowerCase_3, "                   ");
            _builder.append(".get");
            String _firstUpper_16 = StringExtensions.toFirstUpper(f.getName());
            _builder.append(_firstUpper_16, "                   ");
            _builder.append("().addAll(new");
            String _firstUpper_17 = StringExtensions.toFirstUpper(e.getName());
            _builder.append(_firstUpper_17, "                   ");
            _builder.append(".get");
            String _firstUpper_18 = StringExtensions.toFirstUpper(f.getName());
            _builder.append(_firstUpper_18, "                   ");
            _builder.append("());");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("                   ");
            String _lowerCase_4 = e.getName().toLowerCase();
            _builder.append(_lowerCase_4, "                   ");
            _builder.append(".set");
            String _firstUpper_19 = StringExtensions.toFirstUpper(f.getName());
            _builder.append(_firstUpper_19, "                   ");
            _builder.append("(new");
            String _firstUpper_20 = StringExtensions.toFirstUpper(e.getName());
            _builder.append(_firstUpper_20, "                   ");
            _builder.append(".get");
            String _firstUpper_21 = StringExtensions.toFirstUpper(f.getName());
            _builder.append(_firstUpper_21, "                   ");
            _builder.append("());");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("                   ");
    _builder.append("return ");
    String _lowerCase_5 = e.getName().toLowerCase();
    _builder.append(_lowerCase_5, "                   ");
    _builder.append("Repository.save(");
    String _lowerCase_6 = e.getName().toLowerCase();
    _builder.append(_lowerCase_6, "                   ");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("                ");
    _builder.append("})");
    _builder.newLine();
    _builder.append("                ");
    _builder.append(".orElseGet(()->{");
    _builder.newLine();
    _builder.append("                   ");
    _builder.append("new");
    String _firstUpper_22 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_22, "                   ");
    _builder.append(".setId(id);");
    _builder.newLineIfNotEmpty();
    _builder.append("                   ");
    _builder.append("return ");
    String _lowerCase_7 = e.getName().toLowerCase();
    _builder.append(_lowerCase_7, "                   ");
    _builder.append("Repository.save(new");
    String _firstUpper_23 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_23, "                   ");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("                ");
    _builder.append("});");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence compileRepository(final Entity e) {
    StringConcatenation _builder = new StringConcatenation();
    {
      QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(e.eContainer());
      boolean _tripleNotEquals = (_fullyQualifiedName != null);
      if (_tripleNotEquals) {
        _builder.append("package ");
        QualifiedName _fullyQualifiedName_1 = this._iQualifiedNameProvider.getFullyQualifiedName(e.eContainer());
        _builder.append(_fullyQualifiedName_1);
        _builder.append(";");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.example.SzakD_Rest.entities.*;");
    _builder.newLine();
    _builder.append("import org.springframework.data.jpa.repository.JpaRepository;");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("public interface ");
    String _firstUpper = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper);
    _builder.append("Repository extends JpaRepository<");
    String _firstUpper_1 = StringExtensions.toFirstUpper(e.getName());
    _builder.append(_firstUpper_1);
    _builder.append(", Long> {");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
}
