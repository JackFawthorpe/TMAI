<script>
  import { Router, Route } from 'svelte-routing'
  import Navbar from "./Components/Layout/Sidebar.svelte";
  import "./app.css"
  import CardPage from "./Pages/CardPage.svelte";
  import GamePage from "./Pages/GamePage.svelte";
  import HomePage from './Pages/Home.svelte';
  import GameCreatePage from "./Pages/GameCreatePage.svelte";
</script>
<div class="min-h-screen h-full min-w-max bg-gradient-to-r from-green-300 to-blue-500">
  <Router>
    <Navbar/>
    <Route component={HomePage}/>
    <Route path="/cards" component={CardPage}/>
    <Route path="/game" component={GamePage}/>
    <Route path="/game/create" component={GameCreatePage}/>
  </Router>
</div>