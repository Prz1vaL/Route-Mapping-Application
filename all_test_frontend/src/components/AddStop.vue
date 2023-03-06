<template>
  <div>
    <br>
    <h2 style="font-size: 40px">Part Two: Add a New Stop</h2>
    <form>
      <p><strong>Step One:</strong></p>
      <label>
        Route Unique-ID:
        <input type="text" v-model="newStop.uniqueRouteNumber">
      </label>
      <label>
        Route Name
        <input type="text" v-model="newStop.routeName">
      </label>
      <button type="submit" @click.prevent="matchRoute" style="margin-left: 10px">Match Route</button>
      <p><strong>Step Two:</strong></p>
      <label>
        Schedule Identifier
        <input type="text" v-model="newStop.scheduleIdentifier">
      </label>
      <label>
        Stop Name:
        <input type="text" v-model="newStop.stopName">
      </label>
      <label>
        Stop Location:
        <input type="text" v-model="newStop.stopLocation">
      </label>
      <p><strong>Step Three:</strong></p>
      <label>
        Date:
        <input type="text" v-model="newStop.date" placeholder="dd-mm-yyyy">
      </label>
      <button type="submit" @click.prevent="getWeek" style="margin-left: 10px">Show Day of Week</button>
      <p><strong>Step Four:</strong></p>
      <label>
        Departure Time:
        <input type="text" v-model="newStop.departureTime" placeholder="hh:mm">
      </label>
      <label>
        Arrival Time:
        <input type="text" v-model="newStop.arrivalTime" placeholder="hh:mm">
      </label>
      <button type="submit" @click.prevent="addStop" style="margin-left: 10px">Add Stop</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'AddStop',
  data() {
    return {
      newStop: {
        uniqueRouteNumber: '',
        routeName: '',
        scheduleIdentifier: '',
        stopName: '',
        stopLocation: '',
        date: '',
        day: '',
        departureTime: '',
        arrivalTime: '',
      },
      date: ""
    }
  },
  methods: {
    matchRoute() {
      return new Promise((resolve, reject) => {
        let matchResult = true;
        if (this.newStop.uniqueRouteNumber.trim() === "") {
          alert("No Route ID is given.");
          matchResult = false;
        } else if (!/^[a-zA-Z0-9]+$/.test(this.newStop.uniqueRouteNumber)) {
          alert("ID is not valid.")
          matchResult = false;
        }

        if (this.newStop.routeName.trim() === "") {
          alert("No Route Name is given.");
          matchResult = false;
        } else if (!/^[a-zA-Z]+$/.test(this.newStop.routeName)) {
          alert("Name is not valid.")
          matchResult = false;
        }

        axios.get('http://localhost:8080/routes')
            .then(response => {
              const routes = response.data
              if (!routes.some(route => route.uniqueRouteNumber.toLowerCase() === this.newStop.uniqueRouteNumber.toLowerCase()) || !routes.some(route => route.routeName.toLowerCase() === this.newStop.routeName.toLowerCase())) {
                alert("Route ID and Route Name does not match.")
                matchResult = false;
              } else {
                alert("Route ID and Route Name matches.")
              }

              if (matchResult) {
                resolve(true);
              } else {
                reject("Matching failed")
              }
            })
            .catch(error => {
              console.log(error);
              alert(error);
              reject(error);
            });
      });
    },

    addStop() {
      this.matchRoute().then(matchResult => {
        if (!matchResult) {
          return;
        }
        console.log(matchResult);

        if (this.newStop.scheduleIdentifier.trim() === "") {
          alert("No Stop ID is given.");
          return;
        } else if (!/^[a-zA-Z0-9]+$/.test(this.newStop.scheduleIdentifier)) {
          alert("ID is not valid.");
          return;
        }
        var _this = this;
        axios.get('http://localhost:8080/stops')
            .then(response => {
              let stops = response.data
              // judge if scheduleIdentifier already exists
              if (stops.some(stop => stop.scheduleIdentifier.toLowerCase() === this.newStop.scheduleIdentifier.toLowerCase())) {
                alert(" Schedule Identifier exists in the system, Create a new Identifier.")
                return;
              }

              if (this.newStop.stopName.trim() === "") {
                alert("No Stop Name is given.");
                return;
              } else if (!/^[a-zA-Z0-9]+$/.test(this.newStop.stopName)) {
                alert("Stop Name is not valid.");
                return;
              }

              if (this.newStop.stopLocation.trim() === "") {
                alert("No Stop Location is given.");
                return;
              } else if (!/^[a-zA-Z0-9]+$/.test(this.newStop.stopLocation)) {
                alert("Stop Location is not valid.");
                return;
              }

              let dataIsValid = this.validateDate(this.newStop.date);

              if (!dataIsValid) {
                alert("Date is not valid. The date format is dd-mm-yyyy");
                return;
              }

              if (this.newStop.departureTime.trim() === "") {
                alert("No departure time is given.");
                return;
              } else if (!/^([01]?[0-9]|2[0-3]):[0-5][0-9]$/.test(this.newStop.departureTime)) {
                alert("Departure time is not valid. The time format is mm:hh");
                return;
              }

              if (this.newStop.arrivalTime.trim() === "") {
                alert("No arrival time is given.");
                return;
              } else if (!/^([01]?[0-9]|2[0-3]):[0-5][0-9]$/.test(this.newStop.arrivalTime)) {
                alert("Arrival time is not valid. The time format is mm:hh");
                return;
              }

              let timeIsValid = this.isDepartureTimeBeforeArrivalTime(this.newStop.departureTime, this.newStop.arrivalTime);

              if (!timeIsValid) {
                alert("Time is not valid. The departure time should be before arrival time");
                return;
              }
              console.log(timeIsValid);
              this.addStopWithDay(this.getDayOfWeek())
            })
      })

    },
    addStopWithDay(day) {
      this.getDayOfWeek().then(response =>{
        this.newStop.day = response.data
        axios.post('http://localhost:8080/stops', this.newStop)
            .then(response => {
              console.log(response)
              this.newStop.uniqueRouteNumber = ''
              this.newStop.routeName = ''
              this.newStop.scheduleIdentifier = ''
              this.newStop.stopName = ''
              this.newStop.stopLocation = ''
              this.newStop.date = ''
              this.newStop.day = ''
              this.newStop.departureTime = ''
              this.newStop.arrivalTime = ''
              this.$emit("freshStopList")
            })
            .catch(error => {
              console.log(error);
              alert(error);
            })
      })
    },

    getDayOfWeek() {
      this.date = this.newStop.date
      let dataIsValid = this.validateDate(this.newStop.date);
      if (!dataIsValid) {
        alert("Date is not valid. The date format is dd-mm-yyyy");
        return;
      }
      return axios.get('http://localhost:8080/stops/' + this.date);
    },
    getWeek(){
      this.getDayOfWeek().then(response => {
        this.newStop.day = response.data
        alert(this.newStop.day);
      })
    },

    validateDate(inputDate) {
      let dateParts = inputDate.split("-");
      let year = parseInt(dateParts[2], 10);
      let month = parseInt(dateParts[1], 10) - 1;
      let day = parseInt(dateParts[0], 10);
      let date = new Date(year, month, day);
      return date.getFullYear() === year && date.getMonth() === month && date.getDate() === day;
    },

    isDepartureTimeBeforeArrivalTime(departureTime, arrivalTime) {
      let departureDate = new Date('2000/01/01 ' + departureTime);
      let arrivalDate = new Date('2000/01/01 ' + arrivalTime);
      return (departureDate < arrivalDate);
    }
  }
}
</script>

<style>

button:hover {
  background-color: lightblue;
}

button:active {
  background-color: darkblue;
}

</style>