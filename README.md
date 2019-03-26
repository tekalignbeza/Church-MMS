#Member Management Service (MMS)

MMS is application intended to register and manager members, record attendance and track contribution of each members. It also allow to schedule meetings and send notification to members of the church. 

###Functional Requirement

####Member Service
		Add New member.
		Update existing members.
		Remove member.
		Ingest batch from existing data source ( other db or excel) 
		Generate digital ID with barcode(send to their mobile) or hard copy
	
####Meeting Service
		
		Schedule new Meetings.
		Send Meeting notification for each members (with their digitalID)
		Track attendance of each member using there barcode 
		
###Non Functional Requirement 
	
		Cloud based service
		Accessibility
		Capacity, current and forecast
		Compliance
		Documentation
		Disaster recovery
		Efficiency
		Effectiveness
		Extensibility
		Fault tolerance
		Interoperability

###Tech Stack

	Java
	Spring boot
	Hibernate / Spring data
	MySql
	Lombok
	Swagger
	Git->Github
	Messaging -> AWS/Kafka
	Docker
	AWS/GCP/Openshift
	

###High level Architectural design 


####Domain driven design (DDD)
	
		Contribution
		
		   |	

		 Member  -  Attendance -  Meeting
		
			     		   |

			       		Schedule
	
	
####Software Implementation layers 
		
		Presentation/View Layer
		
			Controllers + Swagger Documentation + SDK (for other services to consume) 
				Member
				Meeting
				Contribution /TODO
			DTO + Factories

		Business Layer
		
			Service
				Member
				Meeting
				Contribution /TODO
				Attendance
				
			Helper + Common Util classes 
			
		Data Layer 
			
			Repositories
				Member
				Meeting
				Contribution /TODO
				Attendance
			DTO + Factories 
				
		Configuration Layer
			
				Spring configs
				Messaging configs
