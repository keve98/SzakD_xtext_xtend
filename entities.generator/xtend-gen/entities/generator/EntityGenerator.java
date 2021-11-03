package entities.generator;

import entities.entityModel.DataType;
import entities.entityModel.Entity;
import entities.entityModel.Field;
import entities.entityModel.NamedType;
import java.util.ArrayList;
import javax.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class EntityGenerator {
  @Inject
  @Extension
  private IQualifiedNameProvider _iQualifiedNameProvider;
  
  public CharSequence compileEntity(final Entity e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(" \t\t \t\t");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("import java.util.*;");
    _builder.newLine();
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
      boolean _tripleNotEquals = (_baseEntity != null);
      if (_tripleNotEquals) {
        _builder.append("extends ");
        String _name_1 = e.getBaseEntity().getName();
        _builder.append(_name_1, "\t");
        _builder.append(" ");
      }
    }
    _builder.append("{");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    {
      EList<Field> _fields = e.getFields();
      for(final Field f : _fields) {
        {
          boolean _isArray = f.isArray();
          if (_isArray) {
            _builder.append("\t\t");
            _builder.append("private List<");
            DataType _dataType = f.getDataType();
            String _name_2 = ((NamedType) _dataType).getName();
            _builder.append(_name_2, "\t\t");
            _builder.append("> ");
            String _name_3 = f.getName();
            _builder.append(_name_3, "\t\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t\t");
            _builder.append("private ");
            DataType _dataType_1 = f.getDataType();
            String _name_4 = ((NamedType) _dataType_1).getName();
            _builder.append(_name_4, "\t\t");
            _builder.append(" ");
            String _name_5 = f.getName();
            _builder.append(_name_5, "\t\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t\t");
    _builder.newLine();
    {
      EList<Field> _fields_1 = e.getFields();
      for(final Field f_1 : _fields_1) {
        {
          boolean _isArray_1 = f_1.isArray();
          if (_isArray_1) {
            _builder.append("\t\t");
            CharSequence _compileFieldsArray = this.compileFieldsArray(f_1);
            _builder.append(_compileFieldsArray, "\t\t");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t\t");
            CharSequence _compileFields = this.compileFields(f_1);
            _builder.append(_compileFields, "\t\t");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence compileFieldsArray(final Field f) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.newLine();
    _builder.append("public ");
    DataType _dataType = f.getDataType();
    String _name = ((NamedType) _dataType).getName();
    _builder.append(_name);
    _builder.append(" get");
    String _firstUpper = StringExtensions.toFirstUpper(f.getName());
    _builder.append(_firstUpper);
    _builder.append("(){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if(");
    String _name_1 = f.getName();
    _builder.append(_name_1, "\t");
    _builder.append(" == null){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    String _name_2 = f.getName();
    _builder.append(_name_2, "\t\t");
    _builder.append(" = new ArrayList<");
    DataType _dataType_1 = f.getDataType();
    String _name_3 = ((NamedType) _dataType_1).getName();
    _builder.append(_name_3, "\t\t");
    _builder.append(">();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return ");
    String _name_4 = f.getName();
    _builder.append(_name_4, "\t");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence compileFields(final Field f) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.newLine();
    _builder.append("public ");
    DataType _dataType = f.getDataType();
    String _name = ((NamedType) _dataType).getName();
    _builder.append(_name);
    _builder.append(" get");
    String _firstUpper = StringExtensions.toFirstUpper(f.getName());
    _builder.append(_firstUpper);
    _builder.append("(){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("return ");
    String _name_1 = f.getName();
    _builder.append(_name_1, "\t");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("public void set");
    String _firstUpper_1 = StringExtensions.toFirstUpper(f.getName());
    _builder.append(_firstUpper_1);
    _builder.append("(");
    DataType _dataType_1 = f.getDataType();
    String _name_2 = ((NamedType) _dataType_1).getName();
    _builder.append(_name_2);
    _builder.append(" ");
    String _name_3 = f.getName();
    _builder.append(_name_3);
    _builder.append("){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("this.");
    String _name_4 = f.getName();
    _builder.append(_name_4, "\t");
    _builder.append(" = ");
    String _name_5 = f.getName();
    _builder.append(_name_5, "\t");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence compileController(final Entity e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
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
  
  public CharSequence compileFrontend(final ArrayList<Entity> e) {
    CharSequence _xblockexpression = null;
    {
      boolean isArray = false;
      boolean hasExtends = false;
      String extendName = "";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package com.example.SzakD_Rest.web;");
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _builder.append("import com.example.SzakD_Rest.entities.Blog;");
      _builder.newLine();
      _builder.append("import com.example.SzakD_Rest.entities.Comment;");
      _builder.newLine();
      _builder.append("import com.example.SzakD_Rest.entities.HasAuthor;");
      _builder.newLine();
      _builder.append("import com.example.SzakD_Rest.entities.Post;");
      _builder.newLine();
      _builder.append("import com.example.SzakD_Rest.repositories.BlogRepository;");
      _builder.newLine();
      _builder.append("import com.example.SzakD_Rest.repositories.CommentRepository;");
      _builder.newLine();
      _builder.append("import com.example.SzakD_Rest.repositories.HasAuthorRepository;");
      _builder.newLine();
      _builder.append("import com.example.SzakD_Rest.repositories.PostRepository;");
      _builder.newLine();
      _builder.append("import com.example.SzakD_Rest.services.BlogService;");
      _builder.newLine();
      _builder.append("import com.example.SzakD_Rest.services.CommentService;");
      _builder.newLine();
      _builder.append("import com.example.SzakD_Rest.services.HasAuthorService;");
      _builder.newLine();
      _builder.append("import com.example.SzakD_Rest.services.PostService;");
      _builder.newLine();
      _builder.append("import org.apache.tomcat.JarScanFilter;");
      _builder.newLine();
      _builder.append("import org.hibernate.secure.internal.JaccSecurityListener;");
      _builder.newLine();
      _builder.append("import org.springframework.beans.factory.annotation.Autowired;");
      _builder.newLine();
      _builder.append("import org.springframework.stereotype.Service;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import javax.faces.bean.ManagedBean;");
      _builder.newLine();
      _builder.append("import javax.faces.bean.SessionScoped;");
      _builder.newLine();
      _builder.append("import java.util.List;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("@Service");
      _builder.newLine();
      _builder.append("@ManagedBean(name = \"JSFData\", eager = true)");
      _builder.newLine();
      _builder.append("@SessionScoped");
      _builder.newLine();
      _builder.append("public class JSFData {");
      _builder.newLine();
      {
        for(final Entity f : e) {
          _builder.append("\t");
          _builder.append("private final ");
          String _firstLower = StringExtensions.toFirstLower(f.getName());
          _builder.append(_firstLower, "\t");
          _builder.append("Repository;");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("public ");
          String _name = f.getName();
          _builder.append(_name, "\t");
          _builder.append("Service ");
          String _firstLower_1 = StringExtensions.toFirstLower(f.getName());
          _builder.append(_firstLower_1, "\t");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("public ");
          String _firstUpper = StringExtensions.toFirstUpper(f.getName());
          _builder.append(_firstUpper, "\t");
          _builder.append(" ");
          String _firstLower_2 = StringExtensions.toFirstLower(f.getName());
          _builder.append(_firstLower_2, "\t");
          _builder.append(" = new ");
          String _name_1 = f.getName();
          _builder.append(_name_1, "\t");
          _builder.append("();");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          _builder.newLine();
        }
      }
      _builder.append("    ");
      _builder.newLine();
      _builder.append("    ");
      _builder.newLine();
      {
        for(final Entity ent : e) {
          {
            EList<Field> _fields = ent.getFields();
            for(final Field f_1 : _fields) {
              {
                boolean _isArray = f_1.isArray();
                if (_isArray) {
                  String _xblockexpression_1 = null;
                  {
                    isArray = true;
                    _xblockexpression_1 = "";
                  }
                  _builder.append(_xblockexpression_1);
                  _builder.newLineIfNotEmpty();
                }
              }
            }
          }
          {
            if (isArray) {
              _builder.append("    ");
              _builder.append("Long ");
              String _firstLower_3 = StringExtensions.toFirstLower(ent.getName());
              _builder.append(_firstLower_3);
              _builder.append("id;");
              _builder.newLineIfNotEmpty();
              String _xblockexpression_2 = null;
              {
                isArray = false;
                _xblockexpression_2 = "";
              }
              _builder.append(_xblockexpression_2);
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
      _builder.append("    ");
      _builder.newLine();
      {
        for(final Entity ent_1 : e) {
          {
            Entity _baseEntity = ent_1.getBaseEntity();
            boolean _tripleNotEquals = (_baseEntity != null);
            if (_tripleNotEquals) {
              {
                String _name_2 = ent_1.getBaseEntity().getName();
                if ((extendName != _name_2)) {
                  _builder.append("    ");
                  _builder.append("String ");
                  String _lowerCase = ent_1.getBaseEntity().getName().toLowerCase();
                  _builder.append(_lowerCase, "    ");
                  _builder.append("name;");
                  _builder.newLineIfNotEmpty();
                  _builder.append("    ");
                  String _xblockexpression_3 = null;
                  {
                    extendName = ent_1.getBaseEntity().getName();
                    _xblockexpression_3 = "";
                  }
                  _builder.append(_xblockexpression_3, "    ");
                  _builder.newLineIfNotEmpty();
                }
              }
            }
          }
        }
      }
      _builder.append("    ");
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("@Autowired");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("public JSFData(");
      {
        for(final Entity ent_2 : e) {
          String _name_3 = ent_2.getName();
          _builder.append(_name_3, "    ");
          _builder.append("Repository ");
          String _firstLower_4 = StringExtensions.toFirstLower(ent_2.getName());
          _builder.append(_firstLower_4, "    ");
          _builder.append(", ");
        }
      }
      _builder.append("){");
      _builder.newLineIfNotEmpty();
      {
        for(final Entity ent_3 : e) {
          _builder.append("    \t");
          _builder.append("this.");
          String _firstLower_5 = StringExtensions.toFirstLower(ent_3.getName());
          _builder.append(_firstLower_5, "    \t");
          _builder.append("Repository = ");
          String _firstLower_6 = StringExtensions.toFirstLower(ent_3.getName());
          _builder.append(_firstLower_6, "    \t");
          _builder.newLineIfNotEmpty();
        }
      }
      {
        for(final Entity f_2 : e) {
          _builder.append("        ");
          _builder.append("this.");
          String _lowerCase_1 = f_2.getName().toLowerCase();
          _builder.append(_lowerCase_1, "        ");
          _builder.append("Service = new ");
          String _firstUpper_1 = StringExtensions.toFirstUpper(f_2.getName());
          _builder.append(_firstUpper_1, "        ");
          _builder.append("Service(this.");
          String _lowerCase_2 = f_2.getName().toLowerCase();
          _builder.append(_lowerCase_2, "        ");
          _builder.append("Repositroy);");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("    ");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("//getters, setters");
      _builder.newLine();
      _builder.append("    ");
      _builder.newLine();
      {
        for(final Entity f_3 : e) {
          _builder.append("    ");
          _builder.append("public ");
          String _firstUpper_2 = StringExtensions.toFirstUpper(f_3.getName());
          _builder.append(_firstUpper_2, "    ");
          _builder.append(" get");
          String _firstUpper_3 = StringExtensions.toFirstUpper(f_3.getName());
          _builder.append(_firstUpper_3, "    ");
          _builder.append("(){");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append("\t");
          _builder.append("return ");
          String _firstLower_7 = StringExtensions.toFirstLower(f_3.getName());
          _builder.append(_firstLower_7, "    \t");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append("}");
          _builder.newLine();
        }
      }
      _builder.append("    ");
      _builder.newLine();
      {
        for(final Entity f_4 : e) {
          _builder.append("    ");
          _builder.append("public void set");
          String _firstUpper_4 = StringExtensions.toFirstUpper(f_4.getName());
          _builder.append(_firstUpper_4, "    ");
          _builder.append("(");
          String _name_4 = f_4.getName();
          _builder.append(_name_4, "    ");
          _builder.append(" ");
          String _firstLower_8 = StringExtensions.toFirstLower(f_4.getName());
          _builder.append(_firstLower_8, "    ");
          _builder.append("){");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append("\t");
          _builder.append("this.");
          String _firstLower_9 = StringExtensions.toFirstLower(f_4.getName());
          _builder.append(_firstLower_9, "    \t");
          _builder.append(" = ");
          String _firstLower_10 = StringExtensions.toFirstLower(f_4.getName());
          _builder.append(_firstLower_10, "    \t");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append("}");
          _builder.newLine();
          _builder.append("    ");
          _builder.newLine();
        }
      }
      _builder.append("    ");
      _builder.newLine();
      _builder.append("    ");
      _builder.newLine();
      {
        for(final Entity ent_4 : e) {
          {
            EList<Field> _fields_1 = ent_4.getFields();
            for(final Field f_5 : _fields_1) {
              {
                boolean _isArray_1 = f_5.isArray();
                if (_isArray_1) {
                  String _xblockexpression_4 = null;
                  {
                    isArray = true;
                    _xblockexpression_4 = "";
                  }
                  _builder.append(_xblockexpression_4);
                  _builder.newLineIfNotEmpty();
                }
              }
            }
          }
          {
            if (isArray) {
              _builder.append("    ");
              _builder.append("public Long get");
              String _name_5 = ent_4.getName();
              _builder.append(_name_5, "    ");
              _builder.append("id() {");
              _builder.newLineIfNotEmpty();
              _builder.append("    ");
              _builder.append("\t");
              _builder.append("return ");
              String _firstLower_11 = StringExtensions.toFirstLower(ent_4.getName());
              _builder.append(_firstLower_11, "    \t");
              _builder.append("id;");
              _builder.newLineIfNotEmpty();
              _builder.append("    ");
              _builder.append("}");
              _builder.newLine();
              _builder.append("    ");
              _builder.append("\t\t");
              _builder.newLine();
              _builder.append("    ");
              _builder.append("public void set");
              String _name_6 = ent_4.getName();
              _builder.append(_name_6, "    ");
              _builder.append("id(Long id) {");
              _builder.newLineIfNotEmpty();
              _builder.append("    ");
              _builder.append("     ");
              _builder.append("this.");
              String _firstLower_12 = StringExtensions.toFirstLower(ent_4.getName());
              _builder.append(_firstLower_12, "         ");
              _builder.append("id = ");
              String _firstLower_13 = StringExtensions.toFirstLower(ent_4.getName());
              _builder.append(_firstLower_13, "         ");
              _builder.append("id;");
              _builder.newLineIfNotEmpty();
              _builder.append("    ");
              _builder.append("}");
              _builder.newLine();
            }
          }
          _builder.append("    ");
          String _xblockexpression_5 = null;
          {
            isArray = false;
            _xblockexpression_5 = "";
          }
          _builder.append(_xblockexpression_5, "    ");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.newLine();
      _builder.newLine();
      String _xblockexpression_6 = null;
      {
        extendName = "";
        _xblockexpression_6 = "";
      }
      _builder.append(_xblockexpression_6);
      _builder.newLineIfNotEmpty();
      {
        for(final Entity ent_5 : e) {
          {
            Entity _baseEntity_1 = ent_5.getBaseEntity();
            boolean _tripleNotEquals_1 = (_baseEntity_1 != null);
            if (_tripleNotEquals_1) {
              {
                String _name_7 = ent_5.getBaseEntity().getName();
                if ((extendName != _name_7)) {
                  _builder.append("    ");
                  _builder.append("public String get");
                  String _name_8 = ent_5.getBaseEntity().getName();
                  _builder.append(_name_8);
                  _builder.append("name() {");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t");
                  _builder.append("    ", "\t");
                  _builder.append("return ");
                  String _lowerCase_3 = ent_5.getBaseEntity().getName().toLowerCase();
                  _builder.append(_lowerCase_3, "\t");
                  _builder.append("name;");
                  _builder.newLineIfNotEmpty();
                  _builder.append("    ");
                  _builder.append("}");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t\t");
                  _builder.newLine();
                  _builder.append("    ");
                  _builder.append("public void set");
                  String _name_9 = ent_5.getBaseEntity().getName();
                  _builder.append(_name_9);
                  _builder.append("name(String authorname) {");
                  _builder.newLineIfNotEmpty();
                  _builder.append("    ");
                  _builder.append("    ", "    ");
                  _builder.append("this.");
                  String _lowerCase_4 = ent_5.getBaseEntity().getName().toLowerCase();
                  _builder.append(_lowerCase_4, "    ");
                  _builder.append("name = ");
                  String _lowerCase_5 = ent_5.getBaseEntity().getName().toLowerCase();
                  _builder.append(_lowerCase_5, "    ");
                  _builder.append("name;");
                  _builder.newLineIfNotEmpty();
                  _builder.append("    ");
                  _builder.append("}");
                  _builder.newLineIfNotEmpty();
                  String _xblockexpression_7 = null;
                  {
                    extendName = ent_5.getBaseEntity().getName();
                    _xblockexpression_7 = "";
                  }
                  _builder.append(_xblockexpression_7);
                  _builder.newLineIfNotEmpty();
                }
              }
            }
          }
        }
      }
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("//edits");
      _builder.newLine();
      _builder.append("    ");
      _builder.newLine();
      {
        for(final Entity f_6 : e) {
          _builder.append("    ");
          _builder.append("public String set");
          String _name_10 = f_6.getName();
          _builder.append(_name_10, "    ");
          _builder.append("ToEdit(Long id){");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append("\t");
          _builder.append("this.");
          String _firstLower_14 = StringExtensions.toFirstLower(f_6.getName());
          _builder.append(_firstLower_14, "    \t");
          _builder.append(" = this.");
          String _firstLower_15 = StringExtensions.toFirstLower(f_6.getName());
          _builder.append(_firstLower_15, "    \t");
          _builder.append("Service.findById(id);");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append("\t");
          _builder.append("return \"edit");
          String _name_11 = f_6.getName();
          _builder.append(_name_11, "    \t");
          _builder.append(".xhtml\";");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append("}");
          _builder.newLine();
          _builder.append("    ");
          _builder.newLine();
        }
      }
      _builder.append("    ");
      _builder.newLine();
      _builder.append("    ");
      _builder.newLine();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("//add new");
      _builder.newLine();
      _builder.append("    ");
      _builder.newLine();
      {
        for(final Entity ent_6 : e) {
          {
            EList<Field> _fields_2 = ent_6.getFields();
            for(final Field f_7 : _fields_2) {
              {
                boolean _isArray_2 = f_7.isArray();
                if (_isArray_2) {
                  _builder.append("    ");
                  String _xblockexpression_8 = null;
                  {
                    isArray = true;
                    _xblockexpression_8 = "";
                  }
                  _builder.append(_xblockexpression_8, "    ");
                  _builder.newLineIfNotEmpty();
                }
              }
            }
          }
          {
            if (isArray) {
              _builder.append("    ");
              CharSequence _hasArray = this.hasArray(ent_6);
              _builder.append(_hasArray, "    ");
              _builder.newLineIfNotEmpty();
            } else {
              _builder.append("    ");
              CharSequence _notArray = this.notArray(ent_6);
              _builder.append(_notArray, "    ");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("    ");
          String _xblockexpression_9 = null;
          {
            isArray = false;
            _xblockexpression_9 = "";
          }
          _builder.append(_xblockexpression_9, "    ");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.newLine();
      _builder.append("    ");
      _builder.append("//updates");
      _builder.newLine();
      _builder.append("    ");
      _builder.newLine();
      {
        for(final Entity f_8 : e) {
          _builder.append("    ");
          _builder.append("public ");
          String _name_12 = f_8.getName();
          _builder.append(_name_12, "    ");
          _builder.append(" update");
          String _name_13 = f_8.getName();
          _builder.append(_name_13, "    ");
          _builder.append("(");
          String _name_14 = f_8.getName();
          _builder.append(_name_14, "    ");
          _builder.append(" e, Long id){");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append("\t");
          _builder.append("return ");
          String _firstLower_16 = StringExtensions.toFirstLower(f_8.getName());
          _builder.append(_firstLower_16, "    \t");
          _builder.append("Service.update");
          String _name_15 = f_8.getName();
          _builder.append(_name_15, "    \t");
          _builder.append("(e, id);");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append("}");
          _builder.newLine();
          _builder.append("    ");
          _builder.newLine();
        }
      }
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("//deletes");
      _builder.newLine();
      _builder.append("    ");
      _builder.newLine();
      {
        for(final Entity f_9 : e) {
          _builder.append("    ");
          _builder.append("public void delete");
          String _name_16 = f_9.getName();
          _builder.append(_name_16, "    ");
          _builder.append("(Long id){");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append("\t");
          _builder.append("return ");
          String _firstLower_17 = StringExtensions.toFirstLower(f_9.getName());
          _builder.append(_firstLower_17, "    \t");
          _builder.append("Service.delete");
          String _name_17 = f_9.getName();
          _builder.append(_name_17, "    \t");
          _builder.append("(id);");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append("}");
          _builder.newLine();
          _builder.append("    ");
          _builder.newLine();
        }
      }
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  public CharSequence hasArray(final Entity e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    String _name = e.getName();
    _builder.append(_name);
    _builder.append(" new");
    String _name_1 = e.getName();
    _builder.append(_name_1);
    _builder.append("(");
    String _name_2 = e.getName();
    _builder.append(_name_2);
    _builder.append(" p){");
    _builder.newLineIfNotEmpty();
    {
      EList<Field> _fields = e.getFields();
      for(final Field f : _fields) {
        {
          boolean _isArray = f.isArray();
          if (_isArray) {
            _builder.append("\t");
            _builder.append("if(");
            DataType _dataType = f.getDataType();
            String _lowerCase = ((NamedType) _dataType).getName().toLowerCase();
            _builder.append(_lowerCase, "\t");
            _builder.append("id != null){");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            DataType _dataType_1 = f.getDataType();
            String _name_3 = ((NamedType) _dataType_1).getName();
            _builder.append(_name_3, "\t\t");
            _builder.append(" new");
            DataType _dataType_2 = f.getDataType();
            String _name_4 = ((NamedType) _dataType_2).getName();
            _builder.append(_name_4, "\t\t");
            _builder.append(" = this.");
            DataType _dataType_3 = f.getDataType();
            String _lowerCase_1 = ((NamedType) _dataType_3).getName().toLowerCase();
            _builder.append(_lowerCase_1, "\t\t");
            _builder.append("Service.findById(");
            DataType _dataType_4 = f.getDataType();
            String _lowerCase_2 = ((NamedType) _dataType_4).getName().toLowerCase();
            _builder.append(_lowerCase_2, "\t\t");
            _builder.append("id);");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("new");
            DataType _dataType_5 = f.getDataType();
            String _name_5 = ((NamedType) _dataType_5).getName();
            _builder.append(_name_5, "\t\t");
            _builder.append(".add");
            String _name_6 = e.getName();
            _builder.append(_name_6, "\t\t");
            _builder.append("(p);");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
            {
              Entity _baseEntity = e.getBaseEntity();
              boolean _tripleNotEquals = (_baseEntity != null);
              if (_tripleNotEquals) {
                _builder.append("\t");
                _builder.append("p.set");
                String _name_7 = e.getBaseEntity().getName();
                _builder.append(_name_7, "\t");
                _builder.append("(authorname);");
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("\t");
            _builder.append("return ");
            String _firstLower = StringExtensions.toFirstLower(e.getName());
            _builder.append(_firstLower, "\t");
            _builder.append("Service.new");
            String _name_8 = e.getName();
            _builder.append(_name_8, "\t");
            _builder.append("(p);");
            _builder.newLineIfNotEmpty();
            _builder.append("}");
            _builder.newLine();
            _builder.newLine();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence notArray(final Entity e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    String _name = e.getName();
    _builder.append(_name);
    _builder.append(" new");
    String _name_1 = e.getName();
    _builder.append(_name_1);
    _builder.append("(");
    String _name_2 = e.getName();
    _builder.append(_name_2);
    _builder.append(" h){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("return ");
    String _lowerCase = e.getName().toLowerCase();
    _builder.append(_lowerCase, "\t");
    _builder.append("Service.new");
    String _name_3 = e.getName();
    _builder.append(_name_3, "\t");
    _builder.append("(h);");
    _builder.newLineIfNotEmpty();
    _builder.append("}\t");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence listEntityFrontend(final Entity e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<!DOCTYPE html>");
    _builder.newLine();
    _builder.append("<html xmlns=\"http://www.w3.org/1999/xhtml\"");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("xmlns:h=\"http://xmlns.jcp.org/jsf/html\"");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("xmlns:ui=\"http://java.sun.com/jsf/facelets\"");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("xmlns:f=\"http://java.sun.com/jsf/core\"");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("xmlns:p=\"http://www.w3.org/2001/XMLSchema-instance\"");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("p:schemaLocation=\"http://primefaces.org/ui\">");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<h:body>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<p>");
    String _name = e.getName();
    _builder.append(_name, "    ");
    _builder.append(": </p>");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("<h:form id=\"form\">");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<h:button value=\"New\" id=\"new\" outcome=\"new");
    String _name_1 = e.getName();
    _builder.append(_name_1, "        ");
    _builder.append(".xhtml\"></h:button>");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("<h:dataTable id=\"table\" var=\"");
    String _firstLower = StringExtensions.toFirstLower(e.getName());
    _builder.append(_firstLower, "        ");
    _builder.append("\" value=\"#{");
    String _firstLower_1 = StringExtensions.toFirstLower(e.getName());
    _builder.append(_firstLower_1, "        ");
    _builder.append("Service.all");
    String _name_2 = e.getName();
    _builder.append(_name_2, "        ");
    _builder.append("s}\">");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    _builder.newLine();
    {
      EList<Field> _fields = e.getFields();
      for(final Field f : _fields) {
        {
          boolean _isArray = f.isArray();
          if (_isArray) {
            _builder.append("            ");
            _builder.append("<h:column>");
            _builder.newLine();
            _builder.append("            ");
            _builder.append("\t");
            _builder.append("<p:columns value=\"#{");
            String _firstLower_2 = StringExtensions.toFirstLower(e.getName());
            _builder.append(_firstLower_2, "            \t");
            _builder.append(".");
            String _lowerCase = f.getName().toLowerCase();
            _builder.append(_lowerCase, "            \t");
            _builder.append("}\" var=\"");
            String _lowerCase_1 = f.getName().toLowerCase();
            _builder.append(_lowerCase_1, "            \t");
            _builder.append("\">");
            _builder.newLineIfNotEmpty();
            _builder.append("            ");
            _builder.append("\t\t");
            _builder.append("<f:facet name=\"header\">");
            String _name_3 = f.getName();
            _builder.append(_name_3, "            \t\t");
            _builder.append("</f:facet>");
            _builder.newLineIfNotEmpty();
            _builder.append("            ");
            _builder.append("    \t");
            _builder.append("<p:column value=\"#{");
            String _lowerCase_2 = f.getName().toLowerCase();
            _builder.append(_lowerCase_2, "                \t");
            _builder.append(".title}\"></p:column>");
            _builder.newLineIfNotEmpty();
            _builder.append("            ");
            _builder.append("\t");
            _builder.append("</p:columns>");
            _builder.newLine();
            _builder.append("            ");
            _builder.append("</h:column>");
            _builder.newLine();
          } else {
            _builder.append("            ");
            _builder.append("<h:column>");
            _builder.newLine();
            _builder.append("            ");
            _builder.append("\t");
            _builder.append("<f:facet name=\"header\">");
            String _name_4 = f.getName();
            _builder.append(_name_4, "            \t");
            _builder.append("</f:facet>");
            _builder.newLineIfNotEmpty();
            _builder.append("            ");
            _builder.append("\t");
            _builder.append("<h:outputText value=\"#{");
            String _firstLower_3 = StringExtensions.toFirstLower(e.getName());
            _builder.append(_firstLower_3, "            \t");
            _builder.append(".");
            String _lowerCase_3 = f.getName().toLowerCase();
            _builder.append(_lowerCase_3, "            \t");
            _builder.append("}\"/>");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t\t");
            _builder.append("</h:column>");
            _builder.newLine();
          }
        }
      }
    }
    {
      Entity _baseEntity = e.getBaseEntity();
      boolean _tripleNotEquals = (_baseEntity != null);
      if (_tripleNotEquals) {
        _builder.append("            ");
        _builder.append("<h:column>");
        _builder.newLine();
        _builder.append("\t\t                \t");
        _builder.append("<f:facet name=\"header\">");
        String _name_5 = e.getBaseEntity().getName();
        _builder.append(_name_5, "\t\t                \t");
        _builder.append("</f:facet>");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t                \t");
        _builder.append("<h:outputText value=\"#{");
        String _name_6 = e.getName();
        _builder.append(_name_6, "\t\t                \t");
        _builder.append(".");
        String _lowerCase_4 = e.getBaseEntity().getName().toLowerCase();
        _builder.append(_lowerCase_4, "\t\t                \t");
        _builder.append("}\" />");
        _builder.newLineIfNotEmpty();
        _builder.append("            ");
        _builder.append("</h:column>");
        _builder.newLine();
      }
    }
    _builder.append("            ");
    _builder.append("<h:column>");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("<h:commandButton value=\"Edit\" id=\"edit\" action=\"#{JSFData.set");
    String _name_7 = e.getName();
    _builder.append(_name_7, "                ");
    _builder.append("ToEdit(");
    String _firstLower_4 = StringExtensions.toFirstLower(e.getName());
    _builder.append(_firstLower_4, "                ");
    _builder.append(".id)}\" label=\"Edit\"/>");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    _builder.append("</h:column>");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("<h:column>");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("<h:commandButton value=\"Delete\" id=\"delete\" action=\"#{JSFData.delete");
    String _name_8 = e.getName();
    _builder.append(_name_8, "                ");
    _builder.append("(");
    String _firstLower_5 = StringExtensions.toFirstLower(e.getName());
    _builder.append(_firstLower_5, "                ");
    _builder.append(".id)}\" label=\"Delete\"/>");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    _builder.append("</h:column>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("</h:dataTable>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("</h:form>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<h:button value=\"Back\" outcome=\"#{request.contextPath}/index.xhtml\"/>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("</h:body>");
    _builder.newLine();
    _builder.append("</html>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence editEntityFrontend(final Entity e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<!DOCTYPE html>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<html xmlns:h=\"http://java.sun.com/jsf/html\"");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("xmlns:f=\"http://java.sun.com/jsf/core\">");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<h:head>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<title>Edit ");
    String _firstLower = StringExtensions.toFirstLower(e.getName());
    _builder.append(_firstLower, "    ");
    _builder.append("</title>");
    _builder.newLineIfNotEmpty();
    _builder.append("</h:head>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<h:body>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<p>Edit ");
    String _firstLower_1 = StringExtensions.toFirstLower(e.getName());
    _builder.append(_firstLower_1, "    ");
    _builder.append("</p>");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("<h:form>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<h:dataTable var=\"");
    String _firstLower_2 = StringExtensions.toFirstLower(e.getName());
    _builder.append(_firstLower_2, "        ");
    _builder.append("\" value=\"#{JSFData.");
    String _firstLower_3 = StringExtensions.toFirstLower(e.getName());
    _builder.append(_firstLower_3, "        ");
    _builder.append("}\">");
    _builder.newLineIfNotEmpty();
    {
      EList<Field> _fields = e.getFields();
      for(final Field f : _fields) {
        {
          boolean _isArray = f.isArray();
          boolean _not = (!_isArray);
          if (_not) {
            _builder.append("         ");
            _builder.append("<h:column>");
            _builder.newLine();
            _builder.append("         ");
            _builder.append("     ");
            _builder.append("<f:facet name=\"header\">");
            String _name = f.getName();
            _builder.append(_name, "              ");
            _builder.append("</f:facet>");
            _builder.newLineIfNotEmpty();
            _builder.append("         ");
            _builder.append("     ");
            _builder.append("<h:inputText name=\"name\" value=\"#{");
            String _firstLower_4 = StringExtensions.toFirstLower(e.getName());
            _builder.append(_firstLower_4, "              ");
            _builder.append(".");
            String _firstLower_5 = StringExtensions.toFirstLower(f.getName());
            _builder.append(_firstLower_5, "              ");
            _builder.append("}\" />");
            _builder.newLineIfNotEmpty();
            _builder.append("         ");
            _builder.append("</h:column>");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("         ");
    _builder.append("<h:column>");
    _builder.newLine();
    _builder.append("             ");
    _builder.append("<h:commandButton value=\"Save\" id=\"save\" action=\"#{JSFData.update");
    String _name_1 = e.getName();
    _builder.append(_name_1, "             ");
    _builder.append("(");
    String _firstLower_6 = StringExtensions.toFirstLower(e.getName());
    _builder.append(_firstLower_6, "             ");
    _builder.append(", ");
    String _firstLower_7 = StringExtensions.toFirstLower(e.getName());
    _builder.append(_firstLower_7, "             ");
    _builder.append(".id)}\"></h:commandButton>");
    _builder.newLineIfNotEmpty();
    _builder.append("         ");
    _builder.append("</h:column>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("</h:dataTable>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<h:button value=\"Back\" outcome=\"list");
    String _name_2 = e.getName();
    _builder.append(_name_2, "        ");
    _builder.append(".xhtml\"/>");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("</h:form>");
    _builder.newLine();
    _builder.append("</h:body>");
    _builder.newLine();
    _builder.append("</html>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence newEntityFrontend(final Entity e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<html xmlns:h=\"http://java.sun.com/jsf/html\"");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("xmlns:f=\"http://java.sun.com/jsf/core\" xmlns:ui=\"http://java.sun.com/jsf/facelets\">");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<h:head>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<title>New ");
    String _firstLower = StringExtensions.toFirstLower(e.getName());
    _builder.append(_firstLower, "    ");
    _builder.append("</title>");
    _builder.newLineIfNotEmpty();
    _builder.append("</h:head>");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("<h:body>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<p>Add new ");
    String _firstLower_1 = StringExtensions.toFirstLower(e.getName());
    _builder.append(_firstLower_1, "    ");
    _builder.append("</p>");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("<h:form>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<h:dataTable id=\"table\" var=\"");
    String _firstLower_2 = StringExtensions.toFirstLower(e.getName());
    _builder.append(_firstLower_2, "        ");
    _builder.append("\" value=\"#{JSFData.");
    String _firstLower_3 = StringExtensions.toFirstLower(e.getName());
    _builder.append(_firstLower_3, "        ");
    _builder.append("}\">");
    _builder.newLineIfNotEmpty();
    {
      EList<Field> _fields = e.getFields();
      for(final Field f : _fields) {
        {
          boolean _isArray = f.isArray();
          if (_isArray) {
            _builder.append("            ");
            _builder.append("<h:column>");
            _builder.newLine();
            _builder.append("            ");
            _builder.append("    ");
            _builder.append("<f:facet name=\"header\">");
            String _name = f.getName();
            _builder.append(_name, "                ");
            _builder.append("</f:facet>");
            _builder.newLineIfNotEmpty();
            _builder.append("            ");
            _builder.append("    ");
            _builder.append("<h:selectOneListbox id=\"list");
            String _firstLower_4 = StringExtensions.toFirstLower(f.getName());
            _builder.append(_firstLower_4, "                ");
            _builder.append("\" size=\"3\" value=\"#{JSFData.");
            String _firstLower_5 = StringExtensions.toFirstLower(f.getName());
            _builder.append(_firstLower_5, "                ");
            _builder.append("id}\">");
            _builder.newLineIfNotEmpty();
            _builder.append("            ");
            _builder.append("        ");
            _builder.append("<f:selectItems value=\"#{");
            String _firstLower_6 = StringExtensions.toFirstLower(f.getName());
            _builder.append(_firstLower_6, "                    ");
            _builder.append("Service.all");
            String _name_1 = f.getName();
            _builder.append(_name_1, "                    ");
            _builder.append("}\" var=\"");
            String _firstLower_7 = StringExtensions.toFirstLower(f.getName());
            _builder.append(_firstLower_7, "                    ");
            _builder.append("\" itemValue=\"#{");
            String _firstLower_8 = StringExtensions.toFirstLower(f.getName());
            _builder.append(_firstLower_8, "                    ");
            _builder.append(".id}\" itemLabel=\"#{");
            String _firstLower_9 = StringExtensions.toFirstLower(f.getName());
            _builder.append(_firstLower_9, "                    ");
            _builder.append(".title}\"/>");
            _builder.newLineIfNotEmpty();
            _builder.append("            ");
            _builder.append("    ");
            _builder.append("</h:selectOneListbox>");
            _builder.newLine();
            _builder.append("            ");
            _builder.append(" ");
            _builder.append("</h:column>");
            _builder.newLine();
          } else {
            _builder.append("            ");
            _builder.append("<h:column>");
            _builder.newLine();
            _builder.append("            ");
            _builder.append("   ");
            _builder.append("<f:facet name=\"header\">");
            String _name_2 = f.getName();
            _builder.append(_name_2, "               ");
            _builder.append("</f:facet>");
            _builder.newLineIfNotEmpty();
            _builder.append("            ");
            _builder.append("   ");
            _builder.append("<h:inputText value=\"#{");
            String _name_3 = e.getName();
            _builder.append(_name_3, "               ");
            _builder.append(".");
            String _firstLower_10 = StringExtensions.toFirstLower(f.getName());
            _builder.append(_firstLower_10, "               ");
            _builder.append("}\" />");
            _builder.newLineIfNotEmpty();
            _builder.append("            ");
            _builder.append("</h:column>");
            _builder.newLine();
          }
        }
      }
    }
    {
      Entity _baseEntity = e.getBaseEntity();
      boolean _tripleNotEquals = (_baseEntity != null);
      if (_tripleNotEquals) {
        _builder.append("            ");
        _builder.append("<h:column>");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("\t");
        _builder.append("<f:facet name=\"header\">Author</f:facet>");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("\t");
        _builder.append("<h:inputText name=\"name\" value=\"#{JSFData.authorname}\" />");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("</h:column>");
        _builder.newLine();
      }
    }
    _builder.append("            ");
    _builder.append("<h:column>");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("<h:commandButton value=\"Save\" id=\"save\" action=\"#{JSFData.new");
    String _name_4 = e.getName();
    _builder.append(_name_4, "                ");
    _builder.append("(");
    String _firstLower_11 = StringExtensions.toFirstLower(e.getName());
    _builder.append(_firstLower_11, "                ");
    _builder.append(")}\"/>");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    _builder.append("</h:column>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("</h:dataTable>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("</h:form>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<h:button value=\"Back\" outcome=\"list");
    String _name_5 = e.getName();
    _builder.append(_name_5, "    ");
    _builder.append(".xhtml\"/>");
    _builder.newLineIfNotEmpty();
    _builder.append("</h:body>");
    _builder.newLine();
    _builder.append("</html>");
    _builder.newLine();
    return _builder;
  }
}
