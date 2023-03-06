<template>
  <div>
    <h2 style="font-size: 30px">All Times through the Day a Stop Has Service</h2>
    <form>
      <div class="form-group">
        <label>Stop Name:</label>
        <input type="text" class="form-control" v-model="stopName">
      </div>
      <div class="form-group">
        <label>Day:</label>
        <input type="text" class="form-control" v-model="day" placeholder="eg:monday" style="margin-top: 5px;">
      </div>
      <br>
      <button class="btn btn-primary" v-on:click.prevent="getRoutesByStopAndDay">Submit</button>
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
    <br>
    <br>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ViewRoutesByStopAndDay',
  data() {
    return {
      stopName: '',
      day: '',
      routes: []
    };
  },
  methods: {
    getRoutesByStopAndDay() {
      if (this.stopName.trim() === "") {
        alert("No Stop Name is given.");
        return;
      } else if (!/^[a-zA-Z0-9]+$/.test(this.stopName)) {
        alert("Stop Name is not valid.");
        return;
      }

      axios.get(`http://localhost:8080/stops/${this.stopName}/${this.day}/routes`)
          .then(response => {
            this.routes = response.data;

            if(Object.keys(this.routes).length === 0) {
              alert('No such stop name and day matches.');
            }
          })
          .catch(error => {
            alert('No such stop name and day matches.');
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