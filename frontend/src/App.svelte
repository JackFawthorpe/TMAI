<script>
  import { Router, Route } from 'svelte-routing'
  import HomePage from './pages/Home.svelte'
  import Navbar from "./Components/Layout/Navbar.svelte";
  import Footer from "./Components/Layout/Footer.svelte";
  import "./app.css"
  import CardPage from "./Pages/CardPage.svelte";
  import GamePage from "./Pages/GamePage.svelte";
  import GameCreatePage from "./Pages/GameCreatePage.svelte";
</script>
<div class="min-h-screen bg-gradient-to-r from-green-300 to-blue-500 pb-5">
  <Router>
    <Navbar/>
    <Route component={HomePage}/>
    <Route path="/cards" component={CardPage}/>
    <Route path="/game" component={GamePage}/>
    <Route path="/game/create" component={GameCreatePage}/>
    <Footer/>
  </Router>
</div>