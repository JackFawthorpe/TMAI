<script>
    import {Link} from "svelte-routing";
</script>

<nav class="bg-white sticky w-full z-20 border-b border-gray-200">
    <div class="max-w-screen-xl flex flex-wrap items-center justify-center mx-auto p-4">
        <ul class="flex font-medium md:flex-row space-x-8">
            <li>
                <Link to="/cards" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-indigo-800 p-0">Cards</Link>
            </li>
            <li>
                <Link to ="/game" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-indigo-800 p-0">Game</Link>
            </li>
            <li>
                <Link to ="/game/create" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-indigo-800 p-0">New Game</Link>
            </li>
        </ul>
    </div>
</nav>