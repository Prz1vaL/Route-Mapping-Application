<template>
  <div>
    <h2 style="font-size: 30px">All Routes on a Given Stop at a Certain Time of Day</h2>
    <form>
      <div class="form-group">
        <label>Stop Name:</label>
        <input type="text" class="form-control" v-model="stopName">
      </div>
      <div class="form-group">
        <label>Time:</label>
        <input type="text" class="form-control" v-model="time" placeholder="hh:mm" style="margin-top: 5px;">
      </div>
      <br>
      <button class="btn btn-primary" @click.prevent="getRoutesByStopAndTime">Submit</button>
    </form>
    <br>
    <table class="table">
      <thead>
      <tr>
        <th>Route Unique-ID</th>
        <th>Route Name</th>
        <th>Schedule Identifier</th>
        <th>Stop Name</th>
        <th>Stop Location</th>
        <th>Date</th>
        <th>Day</th>
        <th>Departure Time</th>
        <th>Arrival Time</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(route, index) in routes" :key="index">
        <td>{{ route.uniqueRouteNumber }}</td>
        <td>{{ route.routeName }}</td>
        <td>{{ route.scheduleIdentifier }}</td>
        <td>{{ route.stopName }}</td>
        <td>{{ route.stopLocation }}</td>
        <td>{{ route.date }}</td>
        <td>{{ route.day }}</td>
        <td>{{ route.departureTime }}</td>
        <td>{{ route.arrivalTime }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ViewRoutesByStopAndTime',
  data() {
    return {
      stopName: '',
      time: '',
      routes: []
    };
  },
  methods: {
    getRoutesByStopAndTime() {

      if (this.stopName.trim() === "") {
        alert("No Stop Name is given.");
        return;
      } else if (!/^[a-zA-Z0-9]+$/.test(this.stopName)) {
        alert("Stop Name is not valid.");
        return;
      }

      if (this.time.trim() === "") {
        alert("No time is given.");
        return;
      } else if (!/^([01]?[0-9]|2[0-3]):[0-5][0-9]$/.test(this.time)) {
        alert("Time is not valid. The time format is mm:hh");
        return;
      }

      axios.get(`http://localhost:8080/stops/routes/${this.stopName}/${this.time}`)
          .then(response => {
            this.routes = response.data;
            if(Object.keys(this.routes).length === 0) {
              console.log("hello")
              alert('No such stop name and time matches.');
            }
          })
          .catch(error => {
            alert('No such stop name and time matches.');
            this.routes = [];
            console.log(error);
          });
    }
  }
}
</script>

<style scoped>
table {
  border-collapse: collapse;
  width: 80%;
}

th, td {
  text-align: left;
  padding: 8px;
}

th {
  background-color: #f2f2f2;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}


</style>