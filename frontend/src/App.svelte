<script>
  import { Router, Route } from 'svelte-routing'
  import HomePage from './pages/Home.svelte'
  import Navbar from "./Components/Layout/Navbar.svelte";
  import Footer from "./Components/Layout/Footer.svelte";
  import "./app.css"
  import CardPage from "./Pages/CardPage.svelte";
</script>
<div class="min-h-screen bg-gradient-to-r from-green-300 to-blue-500">
  <Router>
    <Navbar/>
    <Route component={HomePage}/>
    <Route path="/cards" component={CardPage}/>
    <Footer/>
  </Router>
</div>