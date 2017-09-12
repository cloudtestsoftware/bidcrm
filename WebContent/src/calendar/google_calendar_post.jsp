<!DOCTYPE html>
<html>
  <head>
    <title>Google Calendar API Quickstart</title>
    <meta charset='utf-8' />
  </head>
  <body>
    <p>Google Calendar API Quickstart</p>

    <!--Add buttons to initiate auth sequence and sign out-->
    <button id="authorize-button" style="display: none;">Authorize</button>
    <button id="signout-button" style="display: none;">Sign Out</button>

    <pre id="content"></pre>

<head>
<script>
// enter the scope of current project (this API must be turned on in the Google console)
var SCOPES = 'https://www.googleapis.com/auth/calendar';

// Client ID and API key from the Developer Console
var CLIENT_ID = '547185786578-f9b77ts1bbrdsuhic16rd9diqgqbmhea.apps.googleusercontent.com';

// Array of API discovery doc URLs for APIs used by the quickstart
var DISCOVERY_DOCS = ["https://www.googleapis.com/discovery/v1/apis/calendar/v3/rest"];

// Authorization scopes required by the API; multiple scopes can be
// included, separated by spaces.
//var SCOPES = "https://www.googleapis.com/auth/calendar.readonly";

var authorizeButton = document.getElementById('authorize-button');
var signoutButton = document.getElementById('signout-button');

/**
 *  On load, called to load the auth2 library and API client library.
 */
 

function handleClientLoad() {
  gapi.load('client:auth2', initClient);
}

/**
 *  Called when the signed in status changes, to update the UI
 *  appropriately. After a sign-in, the API is called.
 */
function updateSigninStatus(isSignedIn) {
  if (isSignedIn) {
    authorizeButton.style.display = 'none';
    signoutButton.style.display = 'block';
    listUpcomingEvents();
  } else {
    authorizeButton.style.display = 'block';
    signoutButton.style.display = 'none';
  }
}

/**
 *  Sign in the user upon button click.
 */
function handleAuthClick(event) {
  gapi.auth2.getAuthInstance().signIn();
}

/**
 *  Sign out the user upon button click.
 */
function handleSignoutClick(event) {
  gapi.auth2.getAuthInstance().signOut();
}

/**
 *  Initializes the API client library and sets up sign-in state
 *  listeners.
 */
function initClient() {
  gapi.client.init({
    discoveryDocs: DISCOVERY_DOCS,
    clientId: CLIENT_ID,
    scope: SCOPES
  }).then(function () {
    // Listen for sign-in state changes.
    gapi.auth2.getAuthInstance().isSignedIn.listen(updateSigninStatus);

    // Handle the initial sign-in state.
    updateSigninStatus(gapi.auth2.getAuthInstance().isSignedIn.get());
    authorizeButton.onclick = handleAuthClick;
    signoutButton.onclick = handleSignoutClick;
  });
}



/**
 * Append a pre element to the body containing the given message
 * as its text node. Used to display the results of the API call.
 *
 * @param {string} message Text to be placed in pre element.
 */
function appendPre(message) {
  var pre = document.getElementById('content');
  var textContent = document.createTextNode(message + '\n');
  pre.appendChild(textContent);
}

/**
 * Print the summary and start datetime/date of the next ten events in
 * the authorized user's calendar. If no events are found an
 * appropriate message is printed.
 */
function listUpcomingEvents() {
  gapi.client.calendar.events.list({
    'calendarId': 'primary',
    'timeMin': (new Date()).toISOString(),
    'showDeleted': false,
    'singleEvents': true,
    'maxResults': 10,
    'orderBy': 'startTime'
  }).then(function(response) {
    var events = response.result.items;
    appendPre('Upcoming events:');

    if (events.length > 0) {
      for (i = 0; i < events.length; i++) {
        var event = events[i];
        var when = event.start.dateTime;
        if (!when) {
          when = event.start.date;
        }
        appendPre(event.summary + ' (' + when + ')')
      }
    } else {
      appendPre('No upcoming events found.');
    }
  });
}
function makeApiCall(){
	var event = {
			  'summary': 'Google I/O 2015',
			  'location': '800 Howard St., San Francisco, CA 94103',
			  'description': 'A chance to hear more about Google\'s developer products.',
			  'start': {
			    'dateTime': '2017-08-28T09:00:00-07:00',
			    'timeZone': 'America/Los_Angeles'
			  },
			  'end': {
			    'dateTime': '2017-08-28T17:00:00-07:00',
			    'timeZone': 'America/Los_Angeles'
			  },
			  'recurrence': [
			    'RRULE:FREQ=DAILY;COUNT=2'
			  ],
			  'attendees': [
			    {'email': 'si721426@gmail.com'},
			    {'email': 'jana.srimanta@gmail.com'}
			  ],
			  'reminders': {
			    'useDefault': false,
			    'overrides': [
			      {'method': 'email', 'minutes': 24 * 60},
			      {'method': 'popup', 'minutes': 10}
			    ]
			  }
			};

			var request = gapi.client.calendar.events.insert({
			  'calendarId': 'primary',
			  'resource': event,
			  'sendNotifications':true,
			});

			request.execute(function(event) {
			  appendPre('Event created: ' + event.htmlLink);
			});
}
</script>
</head>
 <button id="btnCreateEvents" class="btn btn-primary" onclick="makeApiCall();">
      Create Events</button>  

<div id="divifm">

<iframe id="ifmCalendar"  src="https://calendar.google.com/calendar/embed?src=am3ekqrebhfltvj3j8gu9bnnbo%40group.calendar.google.com&ctz=America/Los_Angeles" 
		style="border: 0" width="800" height="600" frameborder="0" scrolling="no"></iframe>
     
  </div>
    <script async defer src="https://apis.google.com/js/api.js"
      onload="this.onload=function(){};handleClientLoad()"
      onreadystatechange="if (this.readyState === 'complete') this.onload()">
      
     
    </script>
  </body>
</html>