<script>
    import {Link} from "svelte-routing";

    let isOpen = false;

    const toggleSidebar = () => {
        isOpen = !isOpen;
    }

</script>

<div
        class="fixed top-0 right-0 h-full w-64 bg-white text-white p-4 transform transition-transform duration-300 ease-in-out z-50 border r"
        class:translate-x-0={isOpen}
        class:translate-x-[100%]={!isOpen}
>
    <ul>
        <li>
            <Link on:click={toggleSidebar} to="/cards" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-indigo-800 p-0">Cards</Link>
            <hr>
        </li>
        <li>
            <Link on:click={toggleSidebar} to="/game" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-indigo-800 p-0">Game</Link>
            <hr>
        </li>
        <li>
            <Link on:click={toggleSidebar} to="/game/create" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-indigo-800 p-0">New Game</Link>
            <hr>
        </li>
    </ul>
</div>

<button class="fixed top-4 right-4 p-2 bg-white rounded-full transition-transform duration-300 ease-in-out"
        on:click={toggleSidebar}
        class:translate-x-[-800%]={isOpen}>
    <svg class="w-4 h-4 text-gray-800 dark:text-white" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 17 14">
        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 1h15M1 7h15M1 13h15"/>
    </svg>
</button>