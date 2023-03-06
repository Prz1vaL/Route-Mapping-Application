<template>
  <div>
    <AddRoute @add-route="addRoute" @freshRouteList="freshRouteList"/>
    <br />
    <br />
    <h2 style="font-size: 30px">Routes List</h2>
    <table>
      <thead>
      <tr>
        <th>Route Unique-ID</th>
        <th>Route Name</th>
        <th>Starting Point</th>
        <th>Destination</th>
        <th>Action</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="route in routes" :key="route.uniqueRouteNumber">
        <td>{{ route.uniqueRouteNumber }}</td>
        <td>{{ route.routeName }}</td>
        <td>{{ route.startingPoint }}</td>
        <td>{{ route.destination }}</td>
        <td><button @click.prevent="deleteRoute(route.uniqueRouteNumber)">Delete</button></td>
      </tr>
      </tbody>
    </table>
    <br>
    <button @click="loadRouteData">Load Route Data</button>
    <button @click="saveRouteData">Save Route Data</button>
  </div>
</template>

<script>
import axios from 'axios'
import AddRoute from '@/components/AddRoute.vue'

export default {
  name: 'RoutesList',
  components: {
    AddRoute
  },
  data() {
    return {
      routes: [],
      fresh : false
    }
  },
  methods: {
    getRoutes() {
      axios.get('http://localhost:8080/routes')
          .then(response => {
            console.log(response)
            this.routes = response.data
          })
          .catch(error => {
            console.log(error)
          })
    },
    addRoute(route) {
      axios.post('http://localhost:8080/routes', route)
          .then(response => {
            console.log(response)
            this.routes.push(response.data)
          })
          .catch(error => {
            console.log(error)
          })
    },
    deleteRoute(uniqueRouteNumber) {
      if(confirm("Do you want to delete the Route?")) {
        axios.delete(`http://localhost:8080/routes/${uniqueRouteNumber}`)
            .then(response => {
              console.log(response)
              this.routes = this.routes.filter(route => route.uniqueRouteNumber !== uniqueRouteNumber)
              alert("The Route has been deleted successfully!")
            })
            .catch(error => {
              console.log(error)
            })
      } else {
        alert("The Route has not been deleted!")
      }
    },
    freshRouteList(){
      this.getRoutes()
    },
    loadRouteData(){
      axios.get("http://localhost:8080/data/route/load").then(response=>{
        console.log(response)
        this.$nextTick(() => {
          this.getRoutes()
        })
      })
    },
    saveRouteData(){
      axios.get("http://localhost:8080/data/route/save").then(response=>{
        console.log(response)
      })
    }
  },

  mounted() {
    this.getRoutes()
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

/*tr {*/
/*  background-color: white;*/
/*}*/

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