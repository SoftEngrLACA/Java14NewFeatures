package main.java.com.wickcentral;

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
		SystemRecord systemRecord = new SystemRecord(57664,
				"MT5510",
				"x55",
				"B57642G574366",
				true
				);
		
		System.out.println("machineID: " + systemRecord.machineID + ", " + 
				"machineType: " + systemRecord.machineType + ", " +
				"machineName: " + systemRecord.machineName + ", " +
				"serialNum: " + systemRecord.serialNum + ", " +
				"avail: " + systemRecord.avail );
		
		System.out.println("toString: " + systemRecord.toString() );
		
		// violate business logic
		SystemRecord otherRecord = new SystemRecord(57664,
				null,
				"x55",
				"B57642G574366",
				true
				);
	}

	public record SystemRecord (int machineID,
			String machineType,
			String machineName,
			String serialNum,
			boolean avail
			) {
		
		/**
		 * add a Compact Constructor
		 * for data validation - build a record which is valid in given business context 
		 */
		public SystemRecord {
			if (Objects.isNull(machineType) || Objects.isNull(machineName) || Objects.isNull(serialNum)) {
				throw new IllegalArgumentException("Invalid system info. Check input values " + this.toString());
			}
		}
	
	}


}
