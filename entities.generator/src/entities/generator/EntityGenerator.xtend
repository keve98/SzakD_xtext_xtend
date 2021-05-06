package entities.generator


import entities.entityModel.Entities
import entities.entityModel.Field
import entities.entityModel.Entity
import entities.entityModel.NamedType
import org.eclipse.xtext.naming.IQualifiedNameProvider
import javax.inject.Inject
import entities.entityModel.NamedElement

class EntityGenerator {
	@Inject extension IQualifiedNameProvider
	def compileEntity(Entity e){
		'''
 		«««IF e.eContainer.fullyQualifiedName !== null»package «e.eContainer.fullyQualifiedName»;«ENDIF»
 		
 	import java.util.*;
	import javax.persistence.*;
	
		@Entity
		public class  «e.name» «IF e.baseEntity !== null»extends «e.baseEntity.name» «ENDIF»{
				
			
			«FOR f: e.fields»
			«IF f.array»
				private List<«(f.dataType as NamedType).name»> «f.name»;
			«ELSE»
				private «(f.dataType as NamedType).name» «f.name»;
			«ENDIF»
			«ENDFOR» 
			
			«FOR f: e.fields»
				«IF f.array»
					«f.compileFieldsArray»
				«ELSE»
					«f.compileFields»
				«ENDIF»
			«ENDFOR» 
		}
		'''
	}
	def compileFieldsArray(Field f){
		'''
		
		
		public «(f.dataType as NamedType).name» get«f.name.toFirstUpper»(){
			if(«f.name» == null){
				«f.name» = new ArrayList<«(f.dataType as NamedType).name»>();
			}
			return «f.name»;
		}
		'''
	}
	
	def compileFields(Field f){
		'''
			

			public «(f.dataType as NamedType).name» get«f.name.toFirstUpper»(){
				return «f.name»;
			}
			
			
			
			public void set«f.name.toFirstUpper»(«(f.dataType as NamedType).name» «f.name»){
				this.«f.name» = «f.name»;
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
		public class «e.name»Controller {
		
			«e.name.toFirstUpper»Service «e.name.toLowerCase»Service;
			
			«e.name.toFirstUpper»Controller(«e.name.toFirstUpper»Service «e.name.toLowerCase»){
				this.«e.name.toFirstUpper»Service = «e.name.toLowerCase»;
			}
			
			
			@GetMapping("/«e.name.toLowerCase»s")
			List<«e.name.toFirstUpper»> all(){
				return «e.name.toLowerCase»Service.getAll«e.name.toFirstUpper»s();
			}
		
			@GetMapping("/«e.name.toLowerCase»s/{id}")
			«e.name.toFirstUpper» findById(@PathVariable Long id){
				return «e.name.toLowerCase»Service.findById(id);
			}
		
			@PostMapping("/«e.name.toLowerCase»s")
			«e.name.toFirstUpper» new«e.name.toFirstUpper»(@RequestBody «e.name.toFirstUpper» «e.name.toLowerCase»){
				return «e.name.toLowerCase»Service.new«e.name.toFirstUpper»(«e.name.toLowerCase»);
			}
		
			@PutMapping("/«e.name.toLowerCase»s/{id}")
			«e.name.toFirstUpper» update«e.name.toFirstUpper»(@RequestBody «e.name.toFirstUpper» new«e.name.toFirstUpper», @RequestParam Long id){
				return «e.name.toLowerCase»Service.update«e.name.toFirstUpper»(new«e.name.toFirstUpper», id);
			}
		
			@DeleteMapping("/«e.name.toLowerCase»s/{id}")
			void delete«e.name.toFirstUpper»(@PathVariable Long id){
				«e.name.toLowerCase»Service.delete«e.name.toFirstUpper»(id);
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
		public class «e.name.toFirstUpper»Service {
		
		    private final «e.name.toFirstUpper»Repository blogRepository;
		
		    @Autowired
		    public «e.name.toFirstUpper»Service(«e.name.toFirstUpper»Repository r){this.blogRepository = r;}
		
		    public List<«e.name.toFirstUpper»> getAll«e.name.toFirstUpper»s(){return blogRepository.findAll();}
		
		    public «e.name.toFirstUpper» new«e.name.toFirstUpper»(«e.name.toFirstUpper» b){return blogRepository.save(b);}
		
		    public «e.name.toFirstUpper» findById(Long id){
		        return blogRepository.findById(id)
		                .orElseThrow(()->new NotFoundEntityException(id));
		    }
		
		    public void delete«e.name.toFirstUpper»(Long id){
		        blogRepository.deleteById(id);
		    }
		
		    public «e.name.toFirstUpper»update «e.name.toFirstUpper»(«e.name.toFirstUpper» new«e.name.toFirstUpper», Long id){
		       
		       
		        return «e.name.toLowerCase»Repository.findById(id)
		                .map(«e.name.toLowerCase» -> {
		                   «FOR f: e.fields»
		                   	«IF f.array»
		                   		«e.name.toLowerCase».get«f.name.toFirstUpper»().clear();
		                   		«e.name.toLowerCase».get«f.name.toFirstUpper»().addAll(new«e.name.toFirstUpper».get«f.name.toFirstUpper»());
		                   	«ELSE»
		                   		«e.name.toLowerCase».set«f.name.toFirstUpper»(new«e.name.toFirstUpper».get«f.name.toFirstUpper»());
		                   	«ENDIF»               
		                   «ENDFOR»
		                   return «e.name.toLowerCase»Repository.save(«e.name.toLowerCase»);
		                })
		                .orElseGet(()->{
		                   new«e.name.toFirstUpper».setId(id);
		                   return «e.name.toLowerCase»Repository.save(new«e.name.toFirstUpper»);
		                });
		    }
		}
		
		'''
	}
	
	def compileRepository(Entity e){
		'''
			
			
			import com.example.SzakD_Rest.entities.*;
			import org.springframework.data.jpa.repository.JpaRepository;
			
			
			public interface «e.name.toFirstUpper»Repository extends JpaRepository<«e.name.toFirstUpper», Long> {
			}
			
		'''
	}
}
