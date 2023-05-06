# DCOMS Assignment 
  The KGF Group Malaysia, a multinational company which focuses mainly on e-commerce. The e-commerce company is best known for its top selling categories like Baby products, Accessories, Home Appliances and Mobile phones through e-commerce platform. At present, the company is using a ODS (Order Delivery System) to handle product delivery, but they feel that the system did not seem to be an easy-to-use platform and it’s not able to handle product delivery as the demand for products are getting increased day by day. So, they would like to have a **ODS (Order Delivery System)** that eliminates the current shortfalls and take control of delivery operations, increase revenue, and delight customers with a single, easy to use platform. 

# **Individual Part**
### Task #1: Creation of Server Class in RMIConnections package
In our proposed ODS System, the Client class looks up the Interface object from the RMI registry, and the Register class binds the Server object to the RMI registry. I am responsible for creating the barebones Server class.

### Task #2: Basic Admin CRUD functions 
I am responsible for creating functions that can perform basic CRUD operations (Create, Read, Update, Delete) on items. These functions will be used to manage the items that will be displayed to clients. The CRUD operations allow admin to create new items, read existing items, update the details of an item, and delete items that are no longer needed. Due to nature the of the distributed system architecture, these functions are designed as methods in Interface and implemented in the Server. 
The functionalities include, but are not limited to:
- Admin Main Menu 
- Admin CRUD Interface
- Add Item 
- Edit/Update Item
- Delete Item
- View Item
- Retrieve Item by ID
- Retrieve all Item IDs (Search Item)
 
### Task #3: Data validation methods in Auth class
Before inserting data into database, the data has to be validated. For this, some methods were created to provide necessary data validation to the inputs. 
- These functionalities include:
- Check valid item name
- Check valid unit price
- Check valid stock amount
- Check if all field objects filled
- Check if any inputs have been changed

### Task #4: Relevant connections to the Database
Some of the CRUD functions above require interactions with the database. First, I will also create a data model that accurately represents the items and their attributes. Afterwards, I will be responsible for establishing a connection to the database where the ITEM table will be initialized and static Item Objects will be stored, ensuring that the data is properly normalized to minimize redundancy and improve data integrity. 
These functionalities include, but are not limited to:
- Item Object
- Initialize Item table with some static values



** Change to master branch for code
