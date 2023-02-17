package com.example.tourismAgency.business.constants.messages;

public class Message {

	public class GlobalMessages{
		public static final String DATA_ADDED = "Daten hinzugefügt";
		public static final String DATA_DELETED = "Daten gelöscht";
		public static final String DATA_UPDATED = "Daten aktualisiert";
		public static final String DATA_BROUGHT = "Daten erhaltet";
		public static final String DATA_LISTED = "Daten gelistet";
		
	}
	
	public class HostelTypeMessages{
		public static final String HOSTEL_TYPE_NOT_FOUND = "Hostel type nicht gefunden";
		
	}
	
	public class FacilityMessages{
		public static final String FACILITY_ALREADY_EXISTS = "Diese Angabe wurde schon hinzugefügt.";
	}
	
	public class HotelMessages{
		public static final String HOTEL_ALREADY_EXISTS = "Diese Hotel wurde schon hinzugefügt.";
		public static final String EMAIL_ALREADY_EXISTS = "Diese Email wurde schon hinzugefügt.";
		public static final String HOTEL_NOT_FOUND = "Hotel nicht gefunden.";
		public static final String IS_ACTIVE_CHANGED= "Aktivstatus geändert.";
	}
	
	public class RoomMessages{
		public static final String BOOKED_STATUS_CHANGED = "Status wurde geändert.";
		public static final String ROOM_ALREADY_BOOKED = "";
		
	}
	
	public class BookMessages{
		public static final String ROOM_IS_ALREADY_BOOKED_IN_SELECTED_DATE_RANGE = "Dieses Hotelzimmer wurde in diesem Zeitraum schon reserviert.";

		public static final String CHECK_OUT_SUCCESS = "CHECK_OUT_SUCCESS";

		public static final String CHECK_DATE = "Bitte kontrollieren Sie die eingegebenen Daten";

		public static final String NUMBER_OF_BEDS_NOT_SUFFICIENT = "Die Anzahl der Betten in diesem Zimmer ist nicht unzureichend";
		
	}
	
}
