//turn the bulb on and off
function bulbOnOffHandler(event) {

    var image = event.target;
    var id = image.id
    var lightBulb = setBulbStatus(id, function (lightBulb) {

        if (lightBulb.on) {
            image.src = 'img/bulb-on.png';
        }
        else {
            image.src = 'img/bulb-off.png';
        }
    });
}

//okCode turn lights on or off
function setBulbStatus(bulbId, okCode) {
    let url = new URL("/setBulb", document.baseURI);
    url.searchParams.append("bulbId", bulbId);

    fetch(url)
        .then(response => {
            return response.json();
        })

        .then(
            (data) => {
                okCode(data);
            },
            (error) => {
            });
}

// change intensity based on slider
var refreshIntervalId = null;

function rangeIntensity(event) {
    let bulb = $(".dimmableBulb.selected");

    // Check if a bulb is selected
    if (bulb.length != 0) {
        let slider = event.target;
        let value = slider.value;
        setOpacity(bulb, value);

        // Prevent the current timer to timeout (when existing)
        if (refreshIntervalId != null) {
            clearTimeout(refreshIntervalId);
        }

        // Start a new timer and execute the code when it expires
        refreshIntervalId = setTimeout(function () {
            let bulbId = bulb.attr("id");
            setIntensityStatus(bulbId, value, function () {
                //DoNothing
            });
        }, 200);
    }
}

// change intensity based on timer
let count = 1
setInterval(function () {
    let bulb = $('.dimmableBulbSensor');
    var date = new Date();
    var second = date.getSeconds()
    var hour = date.getHours()
    let brightness = count / 100 //counting number 100 starts with 0.01 - 1.00 etc

    //LocalStorage
    const storedBrightness = window.localStorage.getItem('opacity');
    if (Number(storedBrightness) >= 1) {
        brightness = storedBrightness;
    }
    else {
        window.localStorage.setItem('opacity', brightness);
    }

    if ((hour > 19 && hour < 23) || (hour > 6 && hour < 9)) {
        console.log(brightness)
        bulb.css({ 'opacity': brightness })
        count++
    }

    if ((hour == 23) || (hour == 9)) {
        bulb.css({ 'opacity': 0 })
        count = 1
    }
//running time every xxx
}, 10000)

function setOpacity(bulb, value) {
    // Opacity is 0.0 - 1;
    // value = 0 - 100
    let opacity = value / 100;
    bulb.css("opacity", opacity);
}

//okCode change intensity
function setIntensityStatus(bulbId, intensity, okCode) {
    let url = new URL("/bulbIntensity", document.baseURI);
    url.searchParams.append("bulbId", bulbId);
    url.searchParams.append("intensity", intensity);

    fetch(url)
        .then(response => {
            return response.json();
        })

        .then(
            (data) => {
                okCode(data);
            },
            (error) => {
            });
}

function positionBulbs() {
    let floorPlan = $("#floorplanContainer")
    let lightbulbOff = "img/bulb-off.png";
    let lightbulbOn = "img/bulb-on.png";

    fetchBulbs(function (bulbs) {
        bulbs.forEach(function (bulb) {
            let img = $("<img>");
            img.attr("id", bulb.id);

            if (bulb.dimmable) {
                // Create an absolute positioned div
                // This is the container for the off light and the on light
                let div = $("<div>");
                div.css({ top: bulb.yPosition, left: bulb.xPosition, position: 'absolute' });

                // Create the off light, this will be the background for the on light.
                // When the on light changes opacity, the off light will be shown more or less
                let imgLightBulbOff = $("<img>")
                imgLightBulbOff.attr("src", lightbulbOff)
                imgLightBulbOff.css({ position: 'absolute', height: 60, width: 60 });
                div.append(imgLightBulbOff)

                // Create the dimmable on light
                img.attr("src", lightbulbOn)
                img.addClass("dimmableBulb");
                img.on("click", bulbSelectHandler);
                img.css({ position: 'absolute', height: 60, width: 60, opacity: bulb.intensity + "%" });
                div.append(img);
                floorPlan.append(div);
            }

            if (bulb.dimmableSensor) {
                // Create an absolute positioned div
                let div = $("<div>");
                div.css({ top: bulb.yPosition, left: bulb.xPosition, position: 'absolute' });

                // Create the off light, this will be the background for the on light.
                // When the timer changes opacity, the off light will be shown more or less
                let imgLightBulbOff = $("<img>")
                imgLightBulbOff.attr("src", lightbulbOff)
                imgLightBulbOff.css({ position: 'absolute', height: 60, width: 60 });
                div.append(imgLightBulbOff)

                // Create the dimmable on light
                img.attr("src", lightbulbOn)
                img.addClass("dimmableBulbSensor");
                img.css({ position: 'absolute', height: 60, width: 60, opacity: bulb.intensity + "%" });
                div.append(img);
                floorPlan.append(div);
            }

            else {
                let lightBulbSrc = bulb.on ? lightbulbOn : lightbulbOff;
                img.attr("src", lightBulbSrc)
                img.css({ top: bulb.yPosition, left: bulb.xPosition, position: 'absolute', height: 60, width: 60 });
                img.on("click", bulbOnOffHandler)
                floorPlan.append(img);
            }
        });
    });
}

function bulbSelectHandler(event) {
    // remove the selected class from the currently selected bulb
    $(".dimmableBulb.selected").removeClass("selected");

    // Get the lightbulb and make it a jQuery object
    let lightBulb = $(event.target);
    // Add class to the bulb which is selected
    lightBulb.addClass("selected");

    let opacity = lightBulb.css("opacity");
    let slider = $("#slider");
    slider.val(opacity * 100);

    let newPosition = lightBulb.position("newPosition");
    let currentPos = $(this).position();
}

function fetchBulbs(okCode) {
    let url = new URL("/bulbs", document.baseURI);

    fetch(url)
        .then(response => {
            return response.json();
        })
        .then((data) => {
            okCode(data);
        },
            (error) => {
                console.log(error);
            });
}

$(document).ready(function () {
    positionBulbs();
    $("#slider").on("input", rangeIntensity)
});