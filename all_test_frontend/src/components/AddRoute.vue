<template>
  <div>
    <h2 style="font-size: 40px">Part One: Add a New Route</h2>
    <form>
      <label>
        Route Unique-ID:
        <input type="text" v-model="newRoute.uniqueRouteNumber">
      </label>
      <label>
        Route Name:
        <input type="text" v-model="newRoute.routeName">
      </label>
      <label>
        Starting Point:
        <input type="text" v-model="newRoute.startingPoint">
      </label>
      <label>
        Destination:
        <input type="text" v-model="newRoute.destination">
      </label>
      <button type="submit" id="addRoute" @click.prevent="addRoute" style="margin-left: 10px">Add Route</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'AddRoute',
  data() {
    return {
      newRoute: {
        uniqueRouteNumber: '',
        routeName: '',
        startingPoint: '',
        destination: ''
      }
    }
  },
  methods: {
    addRoute() {
      if (this.newRoute.uniqueRouteNumber.trim() === "") {
        alert("No Route ID is given.");
        return;
      } else if (!/^[a-zA-Z0-9]+$/.test(this.newRoute.uniqueRouteNumber)) {
        alert("ID is not valid.")
        return
      }

      if (this.newRoute.routeName.trim() === "") {
        alert("No Route Name is given.");
        return;
      }else if (!/^[a-zA-Z]+$/.test(this.newRoute.routeName)) {
        alert("Name is not valid.")
        return
      }

      if (this.newRoute.destination.trim() === "") {
        alert("No Destination is given.");
        return;
      }else if (!/^[a-zA-Z]+$/.test(this.newRoute.destination)) {
        alert("Destination is not valid.")
        return
      }

      if (this.newRoute.startingPoint.trim() === "") {
        alert("No Starting Point is given.");
        return;
      }else if (!/^[a-zA-Z]+$/.test(this.newRoute.startingPoint)) {
        alert("Starting Point is not valid.")
        return
      }

      axios.get('http://localhost:8080/routes')
          .then(response => {
            const routes = response.data
            // judge if uniqueRouteNumber already exists
            if (routes.some(route => route.uniqueRouteNumber === this.newRoute.uniqueRouteNumber)) {
              alert('Route with Unique Route Number already exists.')
              return
            }


            axios.post('http://localhost:8080/routes', this.newRoute)
                .then(response => {
                  console.log(response)
                  this.newRoute.uniqueRouteNumber = ''
                  this.newRoute.routeName = ''
                  this.newRoute.startingPoint = ''
                  this.newRoute.destination = ''
                  this.$emit("freshRouteList")
                  alert("Route Added Successfully!")
                })
                .catch(error => {
                  console.log(error)
                })
          })
    }
  }
}
</script>

<style>
  label {
    max-width: 10%;
    color: black;
  }
  button:hover {
    background-color: lightblue;
  }

  button:active {
    background-color: darkblue;
  }
</style>