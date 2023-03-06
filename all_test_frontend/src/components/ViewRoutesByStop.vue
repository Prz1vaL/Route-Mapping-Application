<template>
  <div class="view-routes-by-stop">
    <br>
    <h2 style="font-size: 40px">Part Three: View Functions</h2>
    <h2 style="font-size: 30px">All Routes on a Given Stop</h2>
    <div class="form">
      <label for="stopName">Stop Name：</label>
      <input id="stopName" v-model="form.stopName">
      <br>
      <br>
      <button @click="viewRoutesByStop">Show Routes By Stop</button>
    </div>
    <br>
    <div class="table">
        <table>
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
    </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ViewRoutesByStop',
  data() {
    return {
      form: {
        stopName: '',
      },
      routes: [],
    };
  },
  methods: {
    viewRoutesByStop() {
      if (this.form.stopName.trim() === "") {
        alert("No Stop Name is given.");
        return;
      } else if (!/^[a-zA-Z0-9]+$/.test(this.form.stopName)) {
        alert("Stop Name is not valid.");
        return;
      }

      axios.get(`http://localhost:8080/stops/${this.form.stopName}/routes`)
          .then(response => {
            this.routes = response.data;
            if(Object.keys(this.routes).length === 0) {
              alert('No Stop exists in the system at the given time. ');
            }
          })
          .catch(error => {
            console.log('Error getting routes information.')
            alert('Error getting routes information.');
          })
        },
  },
};
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