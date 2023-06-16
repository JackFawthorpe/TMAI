<script lang="ts">

    import Loader from "../Components/Layout/Loader.svelte";
    import {API} from "../apis/API";
    import {navigate} from "svelte-routing";

    let game: Game;
    const loadGame = async () => {
        try {
            game = await API.getGame();
        } catch (e) {
            if (e.response.status === 404) {
                navigate("game/create")
            } else {
                throw e
            }
        }
    }

    const promise = loadGame();

</script>

{#await promise}
    <Loader/>
{:then _}
    <div class="container-fluid pt-5">
        {#each game.players as player}
            <h3 class="text-indigo-900 text-center font-bold text-3xl">{player.name}</h3>
        {/each}
    </div>
{:catch error}
    <p style="color: red">{error.message}</p>
{/await}