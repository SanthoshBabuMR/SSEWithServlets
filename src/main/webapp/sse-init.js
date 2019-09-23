console.info("Trigger SSE from client");
var sse = new EventSource('/app-21/sse');

sse.onopen = function(e) {
    console.log('onopen');
    console.log(e);
};

sse.onmessage = function(e) {
    console.log('onmessage');
    console.log(e);
};

sse.addEventListener("FASTLOAD_VALIDATION", function(event) {
  const data = JSON.parse(event.data);
  console.log(data);
});

sse.onerror = function(e) {
    console.log('onerror');
    console.log(e);
};

var gindex = 1;

function generateEvent() {
    gindex++;
    fetch('/app-21/event-gen?index='+gindex).then((res) => {
          return res.text();
    })
    .then((data) => {
         console.log(data);
    });
}

var interalId;
function generateRecurringEvent(interval) {
  if(!interval) {
    interval = 1500;
  }
  interalId = setInterval(function() {
    generateEvent();
  }, interval)
}

function stopRecurringEvent() {
    clearInterval(interval);
}
