<script>
    import {Link} from "svelte-routing";

    let isOpen = false;

    const toggleSidebar = () => {
        isOpen = !isOpen;
    }

</script>

<div
        class="fixed top-0 left-0 h-full w-64 bg-white text-white p-4 transform transition-transform duration-300 ease-in-out z-50"
        class:translate-x-0={isOpen}
        class:translate-x-[-100%]={!isOpen}
>
    <ul class="">
        <li>
            <Link to="/cards" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-indigo-800 p-0">Cards</Link>
            <hr>
        </li>
        <li>
            <Link to ="/game" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-indigo-800 p-0">Game</Link>
            <hr>
        </li>
        <li>
            <Link to ="/game/create" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-indigo-800 p-0">New Game</Link>
            <hr>
        </li>
    </ul>
</div>

<button class="fixed top-4 right-4 p-2 text-white" on:click={toggleSidebar}>
    {#if isOpen}
        Close Sidebar
    {:else}
        Open Sidebar
    {/if}
</button>