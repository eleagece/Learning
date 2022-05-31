# Spring 5 Simple App

## JPA Entities
* Para que los POJOs sean entidades JPA hay que ponerle la anotación @Entity y añadirle un private Long id con las anotaciones @Id y @GeneratedValue(strategy = GenerationType.AUTO) con su getter y setter. Mirar cómo se hace en Eclipse la creación del POJO (Constructor vacío, Constructor con atributos, Getters y Setters).
* Para que la relación muchos a muchos funcione entre los autores y los libros hay que añadir en el Author sobre el Set<Book> books la anotacion @ManyToMany(mappedBy = "Authors").
* En el Book sobre Set<Author> authors hay que poner la anotación @ManyToMany y luego @JoinTable(...).
* El equals() y hashCode() son necesarios para que las entidades puedan diferenciarse en Hibernate. El toString() es bastante útil a la hora de depurar.
 
## Data repositories
* Creamos un nuevo package/carpeta que se llama repositories y vamos a añadir AuthorRepository que heredará de CrudRepository<Author, Long>. El Long es por el id con la anotación @Id del Author.
* `save(S entity), saveAll(Iterable<S> entities), findById(ID id), existsById(ID id), findAll(), findAllById(Iterable<ID> ids), count(), deleteById(ID id), delete(T entity), deleteAll(Iterable<? extends T> entities, deleteAll()`.
* Nosotros sólo tenemos que preocuparnos de proporcionar la interfaz, Spring se ocupa de rellenar todo en tiempo de ejecución.
* Hacemos un BookRepository exactamente igual.
* Spring Data Repositories es una familia de proyectos. Data en concreto es para funcionar con JPA e Hibernate.
* Spring Data abstrae el montón de código ceremonial de JDBC.

## Inicializar Data
* Paquete bootstrap con clase BootStrapData que implemente CommandLineRunner (interfaz que sirve para que Spring busque instancias de este tipo para hacerlas correr. Tienen su método run(String... args)). A la clase BootStrapData Le ponemos la anotación @Component para que Spring la detecte como Spring managed component. Así conseguimos la inyección de dependencias para las variables authorRepository y bookRepository. Eso significa que no tenemos que instanciarlas manualmente, ya que al crear los Author y Book necesarios e insertarlos Spring lo hace por nosotros (por debajo Spring Data JPA usa Hibernate para guardar los objetos en la base de datos en memoria H2).
* Publisher tiene un Set<Book> con la anotación @OneToMany y @JoinColumn(name = "publisher_id"). Esto último hace que los Book tengan foreign key apuntando al id del Publisher. En Book hay que poner un Publisher con la anotación @ManyToOne. Evidentemente en BootStrapData hay que crear el Publisher y ponerselo a los Book.
* Hibernate hace todas las sentencias SQL por nosotros basándose en las definiciones que damos en JPA ya sea los modelos o las relaciones que ponemos entre ellos con las anotaciones @OnetoMany, @JoinColumn etc.

## H2 Database console
* En resources/application.properties ponemos spring.h2.console.enabled = true para poder tener acceso a la base de datos desde localhost:8080/h2-console/. Es importante poner bien la cadena de conexión (jdbc:...), la cual nos sale en la consola al iniciar la aplicación como algo así: H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:testdb'.

## MVC
* Cliente hace HTTP requests al controlador del servidor y este hace lo que considere con el modelo el cual viene de base de datos y se lo manda a la vista para que lo muestre (se puede hacer con Thymeleaf que coge el POJO del modelo y lo pasa a HTML). El modelo se consigue a través de un servicio.
* Nuevo paquete controllers creando el controlador BookController con la anotación @Controller para que Spring sepa que esto va a ser un controlador MVC.
* Hacemos el método getBooks() y lo mapeamos a una ruta con @RequestMapping("/books") con lo que cuando se acceda a esa ruta, Spring llamará a este método. Al BookController le hacemos su constructor en el que rellenaremos un bookRepository. Al tener la etiqueta @Controller cuando Spring instancie, inyectará el bookRepository necesario.
* El método getBooks(Model model) proporciona el modelo que será enviado a la capa de Vista. Este modelo lo poblamos con la información del bookRepository.findAll(). Devolveremos el atributo por el nombre con el que lo añadimos, en este caso "books".
