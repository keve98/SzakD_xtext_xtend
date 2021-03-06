package entities.generator


import entities.entityModel.Entities
import entities.entityModel.Field
import entities.entityModel.Entity
import entities.entityModel.NamedType
import org.eclipse.xtext.naming.IQualifiedNameProvider
import javax.inject.Inject
import entities.entityModel.NamedElement
import java.util.ArrayList

class EntityGenerator {
	@Inject extension IQualifiedNameProvider
	def compileEntity(Entity e){
		'''
 		???IF e.eContainer.fullyQualifiedName !== null?package ?e.eContainer.fullyQualifiedName?;?ENDIF?
 		
 	import java.util.*;
	import javax.persistence.*;
	
		@Entity
		public class  ?e.name? ?IF e.baseEntity !== null?extends ?e.baseEntity.name? ?ENDIF?{
				
			
			?FOR f: e.fields?
			?IF f.array?
				private List<?(f.dataType as NamedType).name?> ?f.name?;
			?ELSE?
				private ?(f.dataType as NamedType).name? ?f.name?;
			?ENDIF?
			?ENDFOR? 
			
			?FOR f: e.fields?
				?IF f.array?
					?f.compileFieldsArray?
				?ELSE?
					?f.compileFields?
				?ENDIF?
			?ENDFOR? 
		}
		'''
	}
	def compileFieldsArray(Field f){
		'''
		
		
		public ?(f.dataType as NamedType).name? get?f.name.toFirstUpper?(){
			if(?f.name? == null){
				?f.name? = new ArrayList<?(f.dataType as NamedType).name?>();
			}
			return ?f.name?;
		}
		'''
	}
	
	def compileFields(Field f){
		'''
			

			public ?(f.dataType as NamedType).name? get?f.name.toFirstUpper?(){
				return ?f.name?;
			}
			
			
			
			public void set?f.name.toFirstUpper?(?(f.dataType as NamedType).name? ?f.name?){
				this.?f.name? = ?f.name?;
			}
		'''
	}
	
	
	
	def compileController(Entity e){
		
		'''
		
		
		import com.example.SzakD_Rest.entities.*;
		import com.example.SzakD_Rest.services.*;
		import org.springframework.web.bind.annotation.*;
		import java.util.List;
		
		
		@RestController
		public class ?e.name?Controller {
		
			?e.name.toFirstUpper?Service ?e.name.toLowerCase?Service;
			
			?e.name.toFirstUpper?Controller(?e.name.toFirstUpper?Service ?e.name.toLowerCase?){
				this.?e.name.toFirstUpper?Service = ?e.name.toLowerCase?;
			}
			
			
			@GetMapping("/?e.name.toLowerCase?s")
			List<?e.name.toFirstUpper?> all(){
				return ?e.name.toLowerCase?Service.getAll?e.name.toFirstUpper?s();
			}
		
			@GetMapping("/?e.name.toLowerCase?s/{id}")
			?e.name.toFirstUpper? findById(@PathVariable Long id){
				return ?e.name.toLowerCase?Service.findById(id);
			}
		
			@PostMapping("/?e.name.toLowerCase?s")
			?e.name.toFirstUpper? new?e.name.toFirstUpper?(@RequestBody ?e.name.toFirstUpper? ?e.name.toLowerCase?){
				return ?e.name.toLowerCase?Service.new?e.name.toFirstUpper?(?e.name.toLowerCase?);
			}
		
			@PutMapping("/?e.name.toLowerCase?s/{id}")
			?e.name.toFirstUpper? update?e.name.toFirstUpper?(@RequestBody ?e.name.toFirstUpper? new?e.name.toFirstUpper?, @RequestParam Long id){
				return ?e.name.toLowerCase?Service.update?e.name.toFirstUpper?(new?e.name.toFirstUpper?, id);
			}
		
			@DeleteMapping("/?e.name.toLowerCase?s/{id}")
			void delete?e.name.toFirstUpper?(@PathVariable Long id){
				?e.name.toLowerCase?Service.delete?e.name.toFirstUpper?(id);
			}
			
		}
		
		'''
	}
	
	def compileService(Entity e){
		'''
		
		
		import com.example.SzakD_Rest.entities.*;
		import com.example.SzakD_Rest.exceptions.NotFoundEntityException;
		import com.example.SzakD_Rest.repositories.*;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.stereotype.Service;
		
		import java.util.List;
		
		
		@Service
		public class ?e.name.toFirstUpper?Service {
		
		    private final ?e.name.toFirstUpper?Repository blogRepository;
		
		    @Autowired
		    public ?e.name.toFirstUpper?Service(?e.name.toFirstUpper?Repository r){this.blogRepository = r;}
		
		    public List<?e.name.toFirstUpper?> getAll?e.name.toFirstUpper?s(){return blogRepository.findAll();}
		
		    public ?e.name.toFirstUpper? new?e.name.toFirstUpper?(?e.name.toFirstUpper? b){return blogRepository.save(b);}
		
		    public ?e.name.toFirstUpper? findById(Long id){
		        return blogRepository.findById(id)
		                .orElseThrow(()->new NotFoundEntityException(id));
		    }
		
		    public void delete?e.name.toFirstUpper?(Long id){
		        blogRepository.deleteById(id);
		    }
		
		    public ?e.name.toFirstUpper?update ?e.name.toFirstUpper?(?e.name.toFirstUpper? new?e.name.toFirstUpper?, Long id){
		       
		       
		        return ?e.name.toLowerCase?Repository.findById(id)
		                .map(?e.name.toLowerCase? -> {
		                   ?FOR f: e.fields?
		                   	?IF f.array?
		                   		?e.name.toLowerCase?.get?f.name.toFirstUpper?().clear();
		                   		?e.name.toLowerCase?.get?f.name.toFirstUpper?().addAll(new?e.name.toFirstUpper?.get?f.name.toFirstUpper?());
		                   	?ELSE?
		                   		?e.name.toLowerCase?.set?f.name.toFirstUpper?(new?e.name.toFirstUpper?.get?f.name.toFirstUpper?());
		                   	?ENDIF?               
		                   ?ENDFOR?
		                   return ?e.name.toLowerCase?Repository.save(?e.name.toLowerCase?);
		                })
		                .orElseGet(()->{
		                   new?e.name.toFirstUpper?.setId(id);
		                   return ?e.name.toLowerCase?Repository.save(new?e.name.toFirstUpper?);
		                });
		    }
		}
		
		'''
	}
	
	def compileRepository(Entity e){
		'''
			
			
			import com.example.SzakD_Rest.entities.*;
			import org.springframework.data.jpa.repository.JpaRepository;
			
			
			public interface ?e.name.toFirstUpper?Repository extends JpaRepository<?e.name.toFirstUpper?, Long> {
			}
			
		'''
	}
	
	def compileFrontend(ArrayList<Entity> e){
		var isArray = false;
		var hasExtends = false;
		var extendName = "";
		'''
		package com.example.SzakD_Rest.web;
		
		
		import com.example.SzakD_Rest.entities.Blog;
		import com.example.SzakD_Rest.entities.Comment;
		import com.example.SzakD_Rest.entities.HasAuthor;
		import com.example.SzakD_Rest.entities.Post;
		import com.example.SzakD_Rest.repositories.BlogRepository;
		import com.example.SzakD_Rest.repositories.CommentRepository;
		import com.example.SzakD_Rest.repositories.HasAuthorRepository;
		import com.example.SzakD_Rest.repositories.PostRepository;
		import com.example.SzakD_Rest.services.BlogService;
		import com.example.SzakD_Rest.services.CommentService;
		import com.example.SzakD_Rest.services.HasAuthorService;
		import com.example.SzakD_Rest.services.PostService;
		import org.apache.tomcat.JarScanFilter;
		import org.hibernate.secure.internal.JaccSecurityListener;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.stereotype.Service;
		
		import javax.faces.bean.ManagedBean;
		import javax.faces.bean.SessionScoped;
		import java.util.List;
		
		@Service
		@ManagedBean(name = "JSFData", eager = true)
		@SessionScoped
		public class JSFData {
			?FOR f : e?
			private final ?f.name.toFirstLower?Repository;
	public ?f.name?Service ?f.name.toFirstLower?;
	public ?f.name.toFirstUpper? ?f.name.toFirstLower? = new ?f.name?();
				
			?ENDFOR?
		    
		    
		    ?FOR ent : e?
		    	?FOR f : ent.fields?
		    		?IF f.array?
						?{isArray = true; ""}?
		    		?ENDIF?
		    	?ENDFOR?
				?IF isArray?
					?"    "?Long ?ent.name.toFirstLower?id;
					?{isArray = false; ""}?
				?ENDIF?
		    ?ENDFOR?
		    
		    ?FOR ent : e?
		    ?IF ent.baseEntity!==null?
		    	?IF {extendName !== ent.baseEntity.name}?
		    		String ?ent.baseEntity.name.toLowerCase?name;
		    		?{extendName = ent.baseEntity.name; ""}?
		    	?ENDIF?
		    ?ENDIF?
		    ?ENDFOR?
		    
		
		
		    @Autowired
		    public JSFData(?FOR ent : e??ent.name?Repository ?ent.name.toFirstLower?, ?ENDFOR?){
		    	?FOR ent : e?
		    	this.?ent.name.toFirstLower?Repository = ?ent.name.toFirstLower?
		    	?ENDFOR?
		        ?FOR f : e?
		        this.?f.name.toLowerCase?Service = new ?f.name.toFirstUpper?Service(this.?f.name.toLowerCase?Repositroy);
		        ?ENDFOR?
		    }
		
		
		
		    //getters, setters
		    
		    ?FOR f: e?
		    	public ?f.name.toFirstUpper? get?f.name.toFirstUpper?(){
		    		return ?f.name.toFirstLower?;
		    	}
		    ?ENDFOR?
		    
		    ?FOR f : e?
		    	public void set?f.name.toFirstUpper?(?f.name? ?f.name.toFirstLower?){
		    		this.?f.name.toFirstLower? = ?f.name.toFirstLower?;
		    	}
		    	
		    ?ENDFOR?
		    
		    
		    ?FOR ent : e?
		       	?FOR f : ent.fields?
		       		?IF f.array?
		    		?{isArray = true; ""}?
		       		?ENDIF?
		       	?ENDFOR?
		    	?IF isArray?
		    		public Long get?ent.name?id() {
		    			return ?ent.name.toFirstLower?id;
		    		}
		    				
		    		public void set?ent.name?id(Long id) {
		    		     this.?ent.name.toFirstLower?id = ?ent.name.toFirstLower?id;
		    		}
		    	?ENDIF?
		    	?{isArray = false; ""}?
		    ?ENDFOR?
		
		
		?{extendName=""; ""}?
		?FOR ent : e?
		    ?IF ent.baseEntity!==null?
		    	?IF {extendName !== ent.baseEntity.name}?
				    		?"    "?public String get?ent.baseEntity.name?name() {
				    			?"    "?return ?ent.baseEntity.name.toLowerCase?name;
				    		?"    "?}
				    				
				    		?"    "?public void set?ent.baseEntity.name?name(String authorname) {
				    		    ?"    "?this.?ent.baseEntity.name.toLowerCase?name = ?ent.baseEntity.name.toLowerCase?name;
				    		?"    "?}
				    		?{extendName = ent.baseEntity.name; ""}?
		    	?ENDIF?
		    ?ENDIF?
		?ENDFOR?
		
			
		    //edits
		    
		    ?FOR f : e?
		    	public String set?f.name?ToEdit(Long id){
		    		this.?f.name.toFirstLower? = this.?f.name.toFirstLower?Service.findById(id);
		    		return "edit?f.name?.xhtml";
		    	}
		    	
		    ?ENDFOR?
		    
		    
		
		    //add new
		    
		    ?FOR ent : e?
		    	?FOR f : ent.fields?
		    		?IF f.array?
		    			?{isArray = true; ""}?
			    	?ENDIF?
		    	?ENDFOR?
		    	?IF isArray?
		    		?ent.hasArray?
		    	?ELSE?
		    		?ent.notArray?
		    	?ENDIF?
		    	?{isArray = false; ""}?
		    ?ENDFOR?
		
		    //updates
		    
		    ?FOR f : e?
		    	public ?f.name? update?f.name?(?f.name? e, Long id){
		    		return ?f.name.toFirstLower?Service.update?f.name?(e, id);
		    	}
		    	
		    ?ENDFOR?
		
		
		
		    //deletes
		    
		    ?FOR f : e?
		    	public void delete?f.name?(Long id){
		    		return ?f.name.toFirstLower?Service.delete?f.name?(id);
		    	}
		    	
		    ?ENDFOR?
		}
		
		
		'''
	}
	
	
	def hasArray(Entity e){
		'''
		public ?e.name? new?e.name?(?e.name? p){
			?FOR f : e.fields?
				?IF f.array?
					if(?(f.dataType as NamedType).name.toLowerCase?id != null){
						?(f.dataType as NamedType).name? new?(f.dataType as NamedType).name? = this.?(f.dataType as NamedType).name.toLowerCase?Service.findById(?(f.dataType as NamedType).name.toLowerCase?id);
						new?(f.dataType as NamedType).name?.add?e.name?(p);
					}
					?IF e.baseEntity !== null?
					p.set?e.baseEntity.name?(authorname);
					?ENDIF?
					return ?e.name.toFirstLower?Service.new?e.name?(p);
}

				?ENDIF?
			?ENDFOR?
		'''
	}
	
	def notArray(Entity e){
		
		'''
		public ?e.name? new?e.name?(?e.name? h){
			return ?e.name.toLowerCase?Service.new?e.name?(h);
		}	
		
		'''
	}
	
	
	def listEntityFrontend(Entity e){
		'''
		<!DOCTYPE html>
		<html xmlns="http://www.w3.org/1999/xhtml"
		      xmlns:h="http://xmlns.jcp.org/jsf/html"
		      xmlns:ui="http://java.sun.com/jsf/facelets"
		      xmlns:f="http://java.sun.com/jsf/core"
		      xmlns:p="http://www.w3.org/2001/XMLSchema-instance"
		      p:schemaLocation="http://primefaces.org/ui">
		
		<h:body>
		    <p>?e.name?: </p>
		    <h:form id="form">
		        <h:button value="New" id="new" outcome="new?e.name?.xhtml"></h:button>
		        <h:dataTable id="table" var="?e.name.toFirstLower?" value="#{?e.name.toFirstLower?Service.all?e.name?s}">
		            
		            ?FOR f : e.fields?
		            	?IF f.array?
		            	<h:column>
		            		<p:columns value="#{?e.name.toFirstLower?.?f.name.toLowerCase?}" var="?f.name.toLowerCase?">
		            			<f:facet name="header">?f.name?</f:facet>
		            	    	<p:column value="#{?f.name.toLowerCase?.title}"></p:column>
		            		</p:columns>
		            	</h:column>
		            	?ELSE?
		            	<h:column>
		            		<f:facet name="header">?f.name?</f:facet>
		            		<h:outputText value="#{?e.name.toFirstLower?.?f.name.toLowerCase?}"/>
			</h:column>
		            ?ENDIF?
		            ?ENDFOR?
		            ?IF e.baseEntity !== null?
		            	<h:column>
		                	<f:facet name="header">?e.baseEntity.name?</f:facet>
		                	<h:outputText value="#{?e.name?.?e.baseEntity.name.toLowerCase?}" />
		            	</h:column>
		            ?ENDIF?
		            <h:column>
		                <h:commandButton value="Edit" id="edit" action="#{JSFData.set?e.name?ToEdit(?e.name.toFirstLower?.id)}" label="Edit"/>
		            </h:column>
		            <h:column>
		                <h:commandButton value="Delete" id="delete" action="#{JSFData.delete?e.name?(?e.name.toFirstLower?.id)}" label="Delete"/>
		            </h:column>
		        </h:dataTable>
		    </h:form>
		    <h:button value="Back" outcome="#{request.contextPath}/index.xhtml"/>
		
		</h:body>
		</html>
		'''
	}
	
	def editEntityFrontend(Entity e){
		'''
		<!DOCTYPE html>
		
		<html xmlns:h="http://java.sun.com/jsf/html"
		      xmlns:f="http://java.sun.com/jsf/core">
		
		<h:head>
		    <title>Edit ?e.name.toFirstLower?</title>
		</h:head>
		
		<h:body>
		    <p>Edit ?e.name.toFirstLower?</p>
		    <h:form>
		        <h:dataTable var="?e.name.toFirstLower?" value="#{JSFData.?e.name.toFirstLower?}">
		         ?FOR f : e.fields?
		         ?IF !f.array?
		         <h:column>
		              <f:facet name="header">?f.name?</f:facet>
		              <h:inputText name="name" value="#{?e.name.toFirstLower?.?f.name.toFirstLower?}" />
		         </h:column>
		         ?ENDIF?
		         ?ENDFOR?
		         <h:column>
		             <h:commandButton value="Save" id="save" action="#{JSFData.update?e.name?(?e.name.toFirstLower?, ?e.name.toFirstLower?.id)}"></h:commandButton>
		         </h:column>
		        </h:dataTable>
		        <h:button value="Back" outcome="list?e.name?.xhtml"/>
		    </h:form>
		</h:body>
		</html>
		'''
	}
	
	def newEntityFrontend(Entity e){
		'''
		<html xmlns:h="http://java.sun.com/jsf/html"
		      xmlns:f="http://java.sun.com/jsf/core" xmlns:ui="http://java.sun.com/jsf/facelets">
		
		<h:head>
		    <title>New ?e.name.toFirstLower?</title>
		</h:head>
		
		
		<h:body>
		    <p>Add new ?e.name.toFirstLower?</p>
		    <h:form>
		        <h:dataTable id="table" var="?e.name.toFirstLower?" value="#{JSFData.?e.name.toFirstLower?}">
		            ?FOR f: e.fields?
		            ?IF f.array?
		            <h:column>
		                <f:facet name="header">?f.name?</f:facet>
		                <h:selectOneListbox id="list?f.name.toFirstLower?" size="3" value="#{JSFData.?f.name.toFirstLower?id}">
		                    <f:selectItems value="#{?f.name.toFirstLower?Service.all?f.name?}" var="?f.name.toFirstLower?" itemValue="#{?f.name.toFirstLower?.id}" itemLabel="#{?f.name.toFirstLower?.title}"/>
		                </h:selectOneListbox>
		             </h:column>
		            ?ELSE?
		             <h:column>
		                <f:facet name="header">?f.name?</f:facet>
		                <h:inputText value="#{?e.name?.?f.name.toFirstLower?}" />
		             </h:column>
		            ?ENDIF?
		            ?ENDFOR?
		            ?IF e.baseEntity !== null?
		            <h:column>
		            	<f:facet name="header">Author</f:facet>
		            	<h:inputText name="name" value="#{JSFData.authorname}" />
		            </h:column>
		            ?ENDIF?
		            <h:column>
		                <h:commandButton value="Save" id="save" action="#{JSFData.new?e.name?(?e.name.toFirstLower?)}"/>
		            </h:column>
		        </h:dataTable>
		    </h:form>
		    <h:button value="Back" outcome="list?e.name?.xhtml"/>
		</h:body>
		</html>
		'''
	}
	
}
