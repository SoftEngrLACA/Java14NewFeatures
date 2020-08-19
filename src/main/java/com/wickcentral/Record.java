package com.wickcentral;

import java.util.Objects;

/**
 * Java 14 New Feature: Record - a preview language feature
 * Used as a plain data carrier; a plain immutable data classes for data transfer between classes and applications
 * Eliminate all the boilerplate code needed to set and get the data from instance
 * Records transfer this responsibility to java compiler which generates the constructor, 
 * field getters, hashCode() and equals() as well toString() methods
 * 
 * Use cases:
 * 
 * When modeling things like domain model classes (potentially to be persisted via ORM), or data transfer objects (DTOs)
 * useful when storing data temporarily. As example can be during JSON deserialization. Generally during 
 * deserialization - do not expect the program to mutate data read from JSON, just read the data and pass it to data processor or validator
 * records are not replacement for mutable Java beans because a record, by design, is immutable
 * when a class is intended to hold the data for some time and we want to avoid writing lots of boilerplate code
 * use records in variety of other situations e.g. hold multiple return values from a method, 
 * stream joins, compound keys and in data-structure such as tree nodes
 *
 */
public class Record {
	
	public static void main(String[] args) {
		
		// Create the record
		SystemRecord systemRecord1 = new SystemRecord(
				57664,
				"MT5510",
				"x55",
				"B57642G574366",
				"John Wick",
				true
				);
		
		System.out.println("machineID: " + systemRecord1.machineID + ", " + 
				"machineType: " + systemRecord1.machineType + ", " +
				"machineName: " + systemRecord1.machineName + ", " +
				"serialNum: " + systemRecord1.serialNum + ", " +
				"ownerName: " + systemRecord1.ownerName + ", " +
				"avail: " + systemRecord1.avail );
		
		System.out.println("systemRecord1 - all parameters present: " + systemRecord1.toString() );
		
		// Create the record with selected parameters
		SystemRecord sampleRecord2 = new SystemRecord(
				57664,
				"MT5510",
				"x55",
				"B57642G574366");
		
		System.out.println("sampleRecord2 with selected parameters: " + sampleRecord2.toString() );
		
		// Create the record: some null parameters, does not violate business logic
		System.out.println("new record violating business logic: ");
		SystemRecord sampleRecord3 = new SystemRecord(
				57664, // machineID
				"MT5510", // machineType
				null, // machineName
				"B57642G574366", // serialNum
				null, // ownerName
				true // avail
				);
		
		System.out.println("sampleRecord3 with selected parameters: " + sampleRecord3.toString() );
		
		// Create the record: violate business logic
		System.out.println("new record violating business logic: ");
		SystemRecord sampleRecord4 = new SystemRecord(
				57664, // machineID
				null, // machineType - violate business logic
				null, // machineName
				"B57642G574366", // serialNum
				null, // ownerName
				true // avail
				);
		
		System.out.println("sampleRecord4 with selected parameters: " + sampleRecord4.toString() );
		
	}

	public record SystemRecord (int machineID,
			String machineType,
			String machineName,
			String serialNum,
			String ownerName,	// optional
			boolean avail
			) {
		
		/**
		 * Add a Compact Constructor
		 * Do data validation - build a record which is valid for a given business context 
		 */
		public SystemRecord {
			Objects.requireNonNull(machineID, "Machine ID is required.");
			Objects.requireNonNull(machineType, "Machine type is required.");
			Objects.requireNonNull(serialNum, "Serial number is required.");
		}
		
		// Declare a constructor with parameters
		public SystemRecord (int machineID,
				String machineType,
				String machineName,
				String serialNum) {
				this(
						machineID, 
						machineType, 
						machineName, 
						serialNum, 
						"NA", 		// default owner value
						false);		// default avail = not avail
			
		}
	
	}


}
