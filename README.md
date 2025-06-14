Instant/Schedule Ride Booking.

Function Requirements:
	1. Schedule Ride Booking: Rider can book a ride, not in past
	2. Assign Driver to Ride:
		- Check overlap with Ride Request departureTime, expectedReachedTime.
	3. Driver Selection Criteria:
		- higher rating
		- lower cancellation rates
		- geographically close (optional)
	4. Handle Cancellations
		- Rider: 
			- Ride is not assigned to driver, anytime he/she can cancel.
			- 1 hr before departureTime
		- Driver:
			- Only assigned rides, 1 hr before.
	5. Trip completion and rating update
		- Trip completion and then rating.
		- Driver avg rating upated.

User Story:
	Rider 
		=> Booking Ride (src, dest, departureTime, expectedReachedTime) Request 
		=> System auto assigned
		=> Driver reached to src, and started the Ride
		=> Ride completed
		=> Rider gives rating to Driver 

Entities:

	User (id, name, phone, email, password)
	Rider ()
	Driver (avgRating, totalCancelledRides, totalAssignedRides, Rating)	
	Ride (id, src, dest, departureTime, expectedReachedTime, startTime, endTime, Status, Rider, Driver, Rating)
	Rating (id, score, comment)

API:
	1. Create Ride request:
		api/ride/requestRide: POST
			{
				src, dest, departureTime, expectedReachedTime, RiderID
			}
		response: 201 CREATED (Ride request created)
				: 404 Not found (Driver)
	2. Assigned Driver:
		- Auto assigned:
			api/ride/auto-assign/{ride_id}
			
		- Manually assigned:
			api/ride/assign/{ride_id}/{driver_id}
	3. Start Ride:
		api/driver/{driver_id}/startRide/{ride_id}
		e.g. api/driver/1/startRide/1
	4. End Ride:
		api/driver/{driver_id}/endRide/{ride_id}
	5. Rating:
		api/rating/{ride_id}: POST
			{
				score, comment
			}

RideStatus: WAITING, ASSIGNED, IN_PROGRESS, COMPLETED, CANCELLED

requestRide:
	Ride => 
	Ride 
	
assignedDriver:
	Ride => WAITING (waiting for driver to be assigned)
	
exceptions:
	InvalidRideStatusException (Handle Ride Status)
	DriverNotAvailbleException (Handle Driver not available case)

services:
	1. Rider
		- createRider
		- updateRider
		- deleteRider
	2. Driver
		- createDriver
		- updateDriver
		- updateDriverRating
	3. Ride 
		- createRide
		- updateRide
		- cancelRide
		- completeRide
		- assignRide
	4. Rating 
		- createRating
		- deleteRating (optional)
	
