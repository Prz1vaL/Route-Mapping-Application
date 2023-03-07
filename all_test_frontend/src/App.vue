<template>
  <div id="app">
    <h1 style="text-align: center; font-size: 48px; color: black">Route Mapping System</h1>
    <RoutesList />
    <StopsList />
    <ViewRoutesByStop />
    <ViewRoutesByStopAndTime />
    <ViewRoutesByStopAndDay />
  </div>
</template>

<script>
import RoutesList from './components/RoutesList.vue'
import StopsList from "./components/StopList.vue";
import ViewRoutesByStop from "./components/ViewRoutesByStop.vue";
import ViewRoutesByStopAndDay from "./components/ViewRouteByStopAndDay.vue";
import ViewRoutesByStopAndTime from "./components/ViewRoutesByStopAndTime.vue";

export default {
  name: 'App',
  components: {
    ViewRoutesByStopAndTime,
    ViewRoutesByStopAndDay,
    ViewRoutesByStop,
    RoutesList,
    StopsList,
  }
}
</script>