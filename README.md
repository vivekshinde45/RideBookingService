# Ride Booking System

A comprehensive ride booking platform that enables instant and scheduled ride bookings with automatic driver assignment, real-time tracking, and rating system.

## Features

- **Instant & Scheduled Ride Booking**: Book rides immediately or schedule for future with validation against past dates
- **Intelligent Driver Assignment**: Automatic driver selection based on rating, cancellation history, and proximity
- **Real-time Ride Management**: Track ride status from booking to completion
- **Flexible Cancellation Policy**: Time-based cancellation rules for both riders and drivers
- **Rating & Review System**: Post-trip rating system with driver performance tracking

## System Architecture

### User Flow
```
Rider → Book Ride → System Auto-Assigns Driver → Driver Reaches Source → 
Trip Starts → Trip Completes → Rider Rates Driver → System Updates Driver Rating
```

### Core Entities

#### User
- `id`: Unique identifier
- `name`: User's full name
- `phone`: Contact number
- `email`: Email address
- `password`: Encrypted password

#### Rider
Inherits from User with ride-specific functionality

#### Driver
- `avgRating`: Average rating from completed trips
- `totalCancelledRides`: Number of cancelled rides
- `totalAssignedRides`: Total rides assigned
- `Rating`: Current rating score

#### Ride
- `id`: Unique ride identifier
- `src`: Source location
- `dest`: Destination location
- `departureTime`: Scheduled departure time
- `expectedReachedTime`: Expected arrival time
- `startTime`: Actual trip start time
- `endTime`: Actual trip end time
- `Status`: Current ride status
- `Rider`: Associated rider
- `Driver`: Assigned driver
- `Rating`: Trip rating

#### Rating
- `id`: Unique rating identifier
- `score`: Numerical rating score
- `comment`: Optional feedback comment

## Business Rules

### Driver Selection Criteria
1. **Higher Rating**: Drivers with better ratings get priority
2. **Lower Cancellation Rates**: Drivers with fewer cancellations preferred
3. **Geographic Proximity**: Nearest available drivers (optional)

### Cancellation Policy
#### Rider Cancellations
- Can cancel anytime if ride is not assigned to driver
- Can cancel up to 1 hour before departure time

#### Driver Cancellations
- Only for assigned rides
- Must cancel at least 1 hour before departure time

### Ride Status Flow
```
WAITING → ASSIGNED → IN_PROGRESS → COMPLETED
    ↓
CANCELLED (from any status with proper conditions)
```

## API Endpoints

### 1. Create Ride Request
```http
POST /api/ride/requestRide
Content-Type: application/json

{
  "src": "Source Location",
  "dest": "Destination Location", 
  "departureTime": "2024-06-15T10:00:00Z",
  "expectedReachedTime": "2024-06-15T11:00:00Z",
  "RiderID": "rider123"
}
```

**Responses:**
- `201 CREATED`: Ride request created successfully
- `404 NOT FOUND`: Driver not available

### 2. Driver Assignment

#### Auto Assignment
```http
POST /api/ride/auto-assign/{ride_id}
```

#### Manual Assignment
```http
POST /api/ride/assign/{ride_id}/{driver_id}
```

### 3. Start Ride
```http
POST /api/driver/{driver_id}/startRide/{ride_id}

Example: POST /api/driver/1/startRide/1
```

### 4. End Ride
```http
POST /api/driver/{driver_id}/endRide/{ride_id}
```

### 5. Submit Rating
```http
POST /api/rating/{ride_id}
Content-Type: application/json

{
  "score": 5,
  "comment": "Great ride experience!"
}
```

## Ride Status Definitions

- **WAITING**: Ride request created, waiting for driver assignment
- **ASSIGNED**: Driver assigned, waiting for trip to start
- **IN_PROGRESS**: Trip is currently active
- **COMPLETED**: Trip finished successfully
- **CANCELLED**: Trip cancelled by rider or driver

## Service Layer

### Rider Services
- `createRider()`: Register new rider
- `updateRider()`: Update rider information
- `deleteRider()`: Remove rider account

### Driver Services
- `createDriver()`: Register new driver
- `updateDriver()`: Update driver information
- `updateDriverRating()`: Recalculate driver's average rating

### Ride Services
- `createRide()`: Create new ride request
- `updateRide()`: Modify ride details
- `cancelRide()`: Cancel existing ride
- `completeRide()`: Mark ride as completed
- `assignRide()`: Assign driver to ride

### Rating Services
- `createRating()`: Submit new rating
- `deleteRating()`: Remove rating (optional)

## Exception Handling

### InvalidRideStatusException
Thrown when attempting operations on rides with invalid status transitions

### DriverNotAvailableException
Thrown when no suitable drivers are available for assignment
