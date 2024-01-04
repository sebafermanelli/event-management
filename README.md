# solvd-project

## event-management

Practice 1

- Create a database schema using Mysql Workbench for the new hierarchy with at least 12 tables and all relations. The schema should satisfy the 3
  Normal Forms.

Practice 2

- 10 statements for insertion.
- 10 statements for updating.
- 10 statements for deletions.
- 5 alter table.
- 1 big statement to join all tables in the database.
- 5 statements with left, right, inner, outer joins.
- 7 statements with aggregate functions and group by and without having.
- 7 statements with aggregate functions and group by and with having.

Practice 3

- Build hierarchy for Schema from the below course.
- Create DAO classes with necessary interfaces, abstract classes, and Generics. DAO should be scalable and flexible to support another framework
  and another database as well. All CRUD operations should be supported using JDBC. Use connection pool from the below block.
- Implement Service layer with necessary abstraction to be able to switch between databases and frameworks.

- Extra homework:
    - The database scheme consists of four tables:
    - # Product(maker, model, type)
    - # PC(code, model, speed, ram, hd, cd, price)
    - # Laptop(code, model, speed, ram, hd, screen, price)
    - # Printer(code, model, color, type, price)
    - The Product table contains data on the maker, model number, and type of product ('PC', 'Laptop', or 'Printer'). It is assumed that model
      numbers
      in the Product table are unique for all makers and product types. Each personal computer in the PC table is unambiguously identified by a unique
      code, and is additionally characterized by its model (foreign key referring to the Product table), processor speed (in MHz) – speed field, RAM
      capacity (in Mb) - ram, hard disk drive capacity (in Gb) – hd, CD-ROM speed (e.g, '4x') - cd, and its price. The Laptop table is similar to
      the PC table, except that instead of the CD-ROM speed, it contains the screen size (in inches) – screen. For each printer model in the
      Printer table, its output type (‘y’ for color and ‘n’ for monochrome) – color field, printing technology ('Laser', 'Jet', or 'Matrix') –
      type, and price are specified.
        1. Find the model number, speed and hard drive capacity for all the PCs with prices below $500. Result set: model, speed, hd.
        2. List all printer makers.
        3. Find the model number, RAM and screen size of the laptops with prices over $1000.
        4. Find all records from the Printer table containing data about color printers.
        5. Find the model number, speed and hard drive capacity of PCs cheaper than $600 having a 12x or a 24x CD drive.
        6. For each maker producing laptops with a hard drive capacity of 10 Gb or higher, find the speed of such laptops. Result set: maker, speed.
        7. Get the models and prices for all commercially available products (of any type) produced by maker B.
        8. Find the makers of PCs with a processor speed of 450 MHz or more. Result set: maker.
        9. Find the printer models having the highest price. Result set: model, price.
        10. Find out the average speed of PCs.

Practice 4

- Add MyBatis DAOs to the existing hierarchy with the same requirements. Choose any XML or interface mapping.
- Switch service classes to MyBatis.

Practice 5

- Create one XML file and XSD schema for at least 5 classes from the below hierarchy.
- Validate XML file using XSD schema and assigned parser.
- Parse XML file using one of the parsers from the title.

- Add JAXB annotations to the hierarchy. Date, List, and complex objects should be covered.
- Parse XML using JAXB.

- Add Jackson’s annotation to the hierarchy. Date, List, and complex objects should be covered.
- Parse JSON using Jackson.