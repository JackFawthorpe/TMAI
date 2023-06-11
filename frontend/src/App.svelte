<script>
  import { Router, Route } from 'svelte-routing'
  import Home from './pages/Home.svelte'
  import Navbar from "./components/Navbar.svelte";
  import Footer from "./components/Footer.svelte";
</script>

<Router>
  <Navbar/>
  <Route component={Home}/>
  <Footer/>
</Router>