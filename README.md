# Parking Lot Management System

A comprehensive low-level design implementation of a parking lot management system in Java, featuring flexible spot allocation strategies, multiple pricing models, and support for various vehicle types.

## System Overview

This parking lot system is designed with object-oriented principles, supporting multiple floors, different vehicle types, flexible pricing strategies, and various spot allocation algorithms.

## UML Class Diagram

```mermaid
classDiagram
    %% Enums
    class VehicleType {
        <<enumeration>>
        CAR
        BIKE
        ELECTRIC_CAR
        TRUCK
        HANDICAP
    }

    class SpotType {
        <<enumeration>>
        CAR
        BIKE
        ELECTRIC_CAR
        TRUCK
        HANDICAP
    }

    class TicketStatus {
        <<enumeration>>
        ACTIVE
        PAID
        CANCELLED
    }

    %% Core Classes
    class Vehicle {
        -String licensePlate
        -VehicleType type
        +Vehicle(String, VehicleType)
        +getLicensePlate() String
        +getType() VehicleType
    }

    class ParkingSpot {
        <<abstract>>
        -String spotId
        -SpotType type
        -boolean isAvailable
        -Vehicle parkedVehicle
        +ParkingSpot(String, SpotType)
        +getSpotId() String
        +getType() SpotType
        +isAvailable() boolean
        +assignVehicle(Vehicle) void
        +removeVehicle() void
    }

    class CarSpot {
        +CarSpot(String)
    }

    class BikeSpot {
        +BikeSpot(String)
    }

    class ElectricCarSpot {
        -boolean hasCharger
        +ElectricCarSpot(String, boolean)
        +hasCharger() boolean
    }

    class TruckSpot {
        +TruckSpot(String)
    }

    class Ticket {
        -String ticketId
        -long entryTime
        -long exitTime
        -Vehicle vehicle
        -ParkingSpot spot
        -EntryGate entryGate
        -ExitGate exitGate
        -TicketStatus status
        +Ticket(Vehicle, ParkingSpot, EntryGate)
        +setExitGate(ExitGate) void
        +setExitTime(long) void
        +getEntryTime() long
        +getExitTime() long
        +getSpot() ParkingSpot
    }

    class Receipt {
        -String receiptId
        -double amount
        -Ticket ticket
        +Receipt(Ticket, double)
    }

    class ParkingFloor {
        -int floorNumber
        -List~ParkingSpot~ parkingSpots
        +ParkingFloor(int, List~ParkingSpot~)
        +getFloorNumber() int
        +getParkingSpots() List~ParkingSpot~
    }

    class ParkingLot {
        -List~ParkingFloor~ floors
        -SpotInventory inventory
        +ParkingLot(List~ParkingFloor~, SpotInventory)
        +getFloors() List~ParkingFloor~
        +getInventory() SpotInventory
    }

    class SpotInventory {
        -Map~SpotType, List~ParkingSpot~~ availableSpots
        +SpotInventory()
        +getAvailableSpot(SpotType) ParkingSpot
        +markOccupied(ParkingSpot) void
        +markAvailable(ParkingSpot) void
    }

    class EntryGate {
        -int gateId
        +EntryGate(int)
        +requestSpot(Vehicle) Ticket
    }

    class ExitGate {
        -int gateId
        +ExitGate(int)
        +submitTicket(Ticket) Receipt
    }

    class ParkingService {
        -ParkingLot lot
        -SpotAllocationStrategy allocationStrategy
        -PricingStrategy pricingStrategy
        +ParkingService(ParkingLot, SpotAllocationStrategy, PricingStrategy)
        +allocateSpot(Vehicle, EntryGate) Ticket
        +processExit(Ticket, ExitGate) Receipt
    }

    %% Strategy Interfaces
    class SpotAllocationStrategy {
        <<interface>>
        +findSpot(ParkingLot, Vehicle) ParkingSpot
    }

    class PricingStrategy {
        <<interface>>
        +calculatePrice(Ticket) double
    }

    %% Strategy Implementations
    class NearestSpotStrategy {
        +findSpot(ParkingLot, Vehicle) ParkingSpot
    }

    class MostAvailableFloorStrategy {
        +findSpot(ParkingLot, Vehicle) ParkingSpot
    }

    class HourlyPricingStrategy {
        +calculatePrice(Ticket) double
    }

    class FlatRatePricingStrategy {
        +calculatePrice(Ticket) double
    }

    %% Relationships
    Vehicle --> VehicleType : type
    ParkingSpot --> SpotType : type
    Ticket --> TicketStatus : status

    ParkingSpot <|-- CarSpot
    ParkingSpot <|-- BikeSpot
    ParkingSpot <|-- ElectricCarSpot
    ParkingSpot <|-- TruckSpot

    SpotAllocationStrategy <|.. NearestSpotStrategy
    SpotAllocationStrategy <|.. MostAvailableFloorStrategy
    PricingStrategy <|.. HourlyPricingStrategy
    PricingStrategy <|.. FlatRatePricingStrategy

    ParkingLot ||--o{ ParkingFloor : contains
    ParkingLot ||--|| SpotInventory : manages
    ParkingFloor ||--o{ ParkingSpot : contains
    SpotInventory ||--o{ ParkingSpot : tracks

    ParkingService ||--|| ParkingLot : manages
    ParkingService ||--|| SpotAllocationStrategy : uses
    ParkingService ||--|| PricingStrategy : uses

    %% Styling
    classDef enum fill:#e1f5fe
    classDef interface fill:#f3e5f5
    classDef abstract fill:#fff3e0
    classDef concrete fill:#e8f5e8

    class VehicleType,SpotType,TicketStatus enum
    class SpotAllocationStrategy,PricingStrategy interface
    class ParkingSpot abstract
```

## Key Features

### 1. Vehicle Types Support
- **Car**: Standard passenger vehicles
- **Bike**: Two-wheeler vehicles
- **Electric Car**: Electric vehicles with charging capabilities
- **Truck**: Large commercial vehicles
- **Handicap**: Accessible parking spots

### 2. Flexible Spot Allocation Strategies
- **Nearest Spot Strategy**: Allocates the closest available spot
- **Most Available Floor Strategy**: Allocates spots on floors with maximum availability

### 3. Multiple Pricing Models
- **Hourly Pricing**: Charges based on parking duration
- **Flat Rate Pricing**: Fixed pricing regardless of duration

### 4. System Components

#### Core Entities
- **Vehicle**: Represents a vehicle with license plate and type
- **ParkingSpot**: Abstract base class for different spot types
- **Ticket**: Generated when a vehicle enters the parking lot
- **Receipt**: Generated when a vehicle exits and payment is processed

#### Management Components
- **ParkingLot**: Main container holding floors and inventory
- **ParkingFloor**: Represents a single floor with multiple spots
- **SpotInventory**: Manages available and occupied spots
- **ParkingService**: Core service managing parking operations

#### Entry/Exit Points
- **EntryGate**: Handles vehicle entry and ticket generation
- **ExitGate**: Handles vehicle exit and payment processing

## Design Patterns Used

1. **Strategy Pattern**: For spot allocation and pricing strategies
2. **Factory Pattern**: Implicit in spot creation based on types
3. **Template Method Pattern**: In the abstract ParkingSpot class
4. **Enum Pattern**: For type-safe constants

## System Flow

1. **Entry Process**:
   - Vehicle arrives at EntryGate
   - ParkingService allocates appropriate spot using allocation strategy
   - Ticket is generated and spot is marked as occupied

2. **Exit Process**:
   - Vehicle arrives at ExitGate with ticket
   - ParkingService calculates charges using pricing strategy
   - Receipt is generated and spot is freed

## Benefits of This Design

- **Extensibility**: Easy to add new vehicle types, spot types, or strategies
- **Maintainability**: Clear separation of concerns and single responsibility
- **Flexibility**: Support for different business rules through strategy patterns
- **Scalability**: Modular design supports growth and modifications

## Future Enhancements

- Payment gateway integration
- Real-time spot availability dashboard
- Mobile app integration
- Advanced analytics and reporting
- Multi-location support