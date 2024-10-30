### This file contains the notes of building the dao application for InternshipScraper
#### Author: Toothless7788

### JPA
- The annotation ```@JoinColumn``` indicates that this entity is the **owner** of the relationship (that is: the corresponding table has a column with a **foreign key** to the referenced table), whereas the attribute ```mappedBy``` indicates that the entity in this side is the inverse of the relationship, and the owner resides in the "other" entity. This also means that you can access the other table from the class which you've annotated with "mappedBy" (fully *bidirectional* relationship). 
- 
    ```
    // Example
    @Entity
    public class Company {
        @OneToMany(mappedBy = "company",
                orphanRemoval = true,
                fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
        private List<Branch> branches;
    }

    @Entity
    public class Branch {
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "companyId")
        private Company company;
    }
    ```
- Cascade is the arrangement to its child entities when we remove an entity -> [better use it on the **parent** entity](https://stackoverflow.com/questions/13027214/what-is-the-meaning-of-the-cascadetype-all-for-a-manytoone-jpa-association)
- #### Useful attributes
    - ##### ```@OneToMany```
        1. ```orphanRemoval=true```
        2. ```cascade=CascadeType.ALL```
        3. ```fetch=FetchType.LAZY```
        4. ```mappedBy="variableNameInChildEntity"```
    - ##### ```@ManyToOne```
        1. ```fetch=FetchType.LAZY```
    - ##### ```@JoinColumn```
        1. ```name="FK_NAME"```
        2. ```referencedColumnName="FK_REFERENCES_NAME"```
    - ##### ```@Column```
        1. ```length=255```
        2. ```nullable=false```
        3. ```name="SQLColumnName"```
    - ##### ```@Enumerated``` (enum has an integer value in Java so we cannot just pass the Java enum to MySQL enum)
        1. ```EnumType.STRING```: Use the String value of the enum (note that enum String values are **case-sensitive** in MySQL database)
        2. ```EnumType.ORDINAL```: USe the integer value of the Java enum
    - ##### ```@Query```
        1. ```nativeQuery=true```
        2. ```value="MySQL Statement with :paramname"``` (one can define param name with ```@Param("paramName")```)
- #### Composite Primary Key
    - One needs to define a class to represent the composite primary key
    - The class must implement ```java.io.Serializable``` and annotated with ```@Embeddable```
    - Then, in the entity class, one needs to annotate the field with ```@EmbeddedId```
- #### How to solve LazyInitializationException
    - Happens when one tries to access ```toString()```, ```hashCode()``` or ```equal()``` of an entity when the JPA session has expired
    - [Solutions](https://stackoverflow.com/questions/2446590/how-to-correctly-implement-equals-hashcode-and-tostring-for-jpa-entities)
- ```deleteAll(Iterable<Entity> entites)``` will throw an exception if one of the element in ```entites``` is ```null``` -> JPA will not delete anything in the database after throwing the exception
- ```delete(Entity entity)``` will only care about the primary key of the entity to choose which record to be deleted, i.e. if I pass a random entity but with the correct ```Primary Key``` only, JPA will **still** delete the record in database and on the other hand, if I pass an entity that has 99% of the data matched but wrong ```Primary Key``` only, it will **not** be deleted by JPA