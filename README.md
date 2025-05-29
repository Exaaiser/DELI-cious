# DELI-cious: Java CLI Sandwich Ordering Application

This project is a Java-based Command Line Interface (CLI) ordering application developed for a sandwich shop. It allows customers to customize their sandwiches, add drinks and chips, and finalize their orders. A receipt for each completed order is saved to a file, and previous orders can be viewed.

## Features

* **Create New Order:** Allows customers to build new orders with custom sandwiches, drinks, and chips.
* **Sandwich Customization:** Full customization of sandwiches including bread type, size, meats, cheeses, vegetables, and sauces.
* **Extra Toppings:** Options for extra meat and cheese.
* **Toasting Option:** Ability to choose if the sandwich should be toasted.
* **Order Summary:** Display order details and total price before checkout.
* **Receipt Saving:** Completed orders are saved as receipt files (named by date and time) into a `receipts` folder 
* **View Previous Orders:** List and display past order receipts.

Architecture Components
Core Product System

Product (Abstract)

── name: String

── price: double

── getName(): String

── getPrice(): double


**Product Types:

Sandwich - Customizable sandwiches with toppings and sides
Drink - Beverages with size and flavor options
Chip - Snack items with name and price**

Sandwich Class
Sandwich extends Product implements Item

├── size: int

├── breadType: String

├── isToasted: boolean

├── toppings: List<Topping>

├── sauces: List<SauceTopping>

├── sides: List<SideItem>

├── getPrice(): double

├── addTopping(Topping): void

├── addSauce(SauceTopping): void

└── addSide(SideItem): void


Topping Hierarchy
Topping (Abstract)

├── name: String

├── getPrice(): double

└── Implementations:

    ├── CheeseTopping - getPrice(int, boolean): double
    
    ├── MeatTopping - getPrice(int, boolean): double 
    
    ├── RegularTopping - getPrice(): double
    
    └── SauceTopping - getPrice(): double
    

Product Builders:

ChipBuilder - Creates chip products
DrinkBuilder - Creates drink products
SandwichBuilder - Creates sandwich products

📦 Order Management
Order System
Order

├── items: List<Item>

├── addItem(Item): void

├── getTotalPrice(): double

├── displayOrder(): void

├── printReceipt(): void

└── clear(): void

OrderManager


├── saveOrder(Order): void

└── loadPreviousOrders(): List<String>


🖥️ User Interface
UserInterface Class
UserInterface

├── scanner: Scanner

├── orderManager: OrderManager

├── currentOrder: Order

├── start(): void

├── startNewOrder(): void

├── viewPreviousOrders(): void

├── checkout(): void

└── cancelOrder(): void


🔧 Utility Classes
Main - Application entry point with main(String[]): void
ProductMaker - Factory for creating products: createProduct(String, String, double) Product
SideItem - Side dish items with name and pricing

🔗 Key Relationships

Inheritance: Product → Sandwich/Drink/Chip
Composition: Sandwich contains Lists of Toppings, Sauces, and SideItems
Interface Implementation: All products implement the Item interface
Builder Pattern: Separate builders for each product type
Aggregation: Order contains multiple Items

📊 Class Interaction Flow

UserInterface manages the main application flow
Builders create products based on user input
Order aggregates multiple items
OrderManager handles persistence
Toppings add functionality to sandwiches with flexible pricing

## Images about class diagrams 

# CASUAL DIAGRAM
 ![Image Alt](https://raw.githubusercontent.com/Exaaiser/DELI-cious/refs/heads/main/Casual%20Diagram.png)

# DETAILED DIAGRAM
![Image Alt](https://raw.githubusercontent.com/Exaaiser/DELI-cious/refs/heads/main/Detailed%20Diagrams.png)


For Detailed Diagram I got help from Claude

