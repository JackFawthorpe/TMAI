<script>
  import { Router, Route } from 'svelte-routing'
  import Home from './pages/Home.svelte'
  import Navbar from "./components/Navbar.svelte";
  import Footer from "./components/Footer.svelte";
  import "./app.css"
</script>
<div class="min-h-screen bg-gradient-to-r from-green-300 to-blue-500">
  <Router>
    <Navbar/>
    <Route component={Home}/>
    <Footer/>
  </Router>
</div>

