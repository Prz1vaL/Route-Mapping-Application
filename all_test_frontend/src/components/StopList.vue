<template>
  <div>
    <AddStop @add-Stop="addStop" @freshStopList="freshStopList"/>
    <h2 style="font-size: 30px">Stops List</h2>
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
        <th>Action</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="stop in stops" :key="stop.scheduleIdentifier">
        <td>{{ stop.uniqueRouteNumber }}</td>
        <td>{{ stop.routeName }}</td>
        <td>{{ stop.scheduleIdentifier }}</td>
        <td>{{ stop.stopName }}</td>
        <td>{{ stop.stopLocation }}</td>
        <td>{{ stop.date }}</td>
        <td>{{ stop.day }}</td>
        <td>{{ stop.departureTime }}</td>
        <td>{{ stop.arrivalTime }}</td>
        <td><button @click.prevent="deleteStop(stop.scheduleIdentifier)">Delete</button></td>
      </tr>
      </tbody>
    </table>
    <br>
    <button @click="loadStopData">Load Stop Data</button>
    <button @click="saveStopData">Save Stop Data</button>
  </div>
</template>

<script>
import axios from 'axios'
import AddStop from '@/components/AddStop.vue'

export default {
  name: 'StopsList',
  components: {
    AddStop
  },
  data() {
    return {
      stops: [],
      fresh : false
    }
  },
  methods: {
    getStops() {
      axios.get('http://localhost:8080/stops')
          .then(response => {
            console.log(response)
            this.stops = response.data
          })
          .catch(error => {
            console.log(error)
          })
    },
    addStop(stop) {
      axios.post('http://localhost:8080/stops', stop)
          .then(response => {
            console.log(response)
            this.stops.push(response.data)
          })
          .catch(error => {
            console.log(error)
          })
    },
    deleteStop(scheduleIdentifier) {
      axios.delete(`http://localhost:8080/stops/${scheduleIdentifier}`)
          .then(response => {
            console.log(response)
            this.stops = this.stops.filter(stop => stop.scheduleIdentifier !== scheduleIdentifier)
          })
          .catch(error => {
            console.log(error)
          })
    },
    freshStopList(){
      this.getStops()
    },
    loadStopData(){
      axios.get("http://localhost:8080/data/stop/load").then(response=>{
        console.log(response)
        this.$nextTick(() => {
          this.getStops()
        })
      })
    },
    saveStopData(){
      axios.get("http://localhost:8080/data/stop/save").then(response=>{
        console.log(response)
        // this.$nextTick(() => {
        //   this.getStops()
        // })
      })
    }
  },
  mounted() {
    this.getStops()
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

button:hover {
  background-color: lightblue;
}

button:active {
  background-color: darkblue;
}

button {
  font-size: 16px;
  margin-right: 30px;
  padding: 10px 20px;
}
</style>