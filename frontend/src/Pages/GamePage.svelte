<script lang="ts">

    import Loader from "../Components/Layout/Loader.svelte";
    import {API} from "../apis/API";
    import {navigate} from "svelte-routing";
    import PlayerCard from "../Components/Game/PlayerCard.svelte";
    import BoardCard from "../Components/Game/BoardCard.svelte";

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
        <div class="mx-2 grid grid-cols-1 lg:grid-cols-2 gap-y-3 md:gap-x-2">
            <div class="col-span-1 grid grid-cols-2 gap-x-3 gap-y-3">
                {#each game.players as player}
                    <PlayerCard player={player}/>
                {/each}
            </div>
            <div class="col-span-1 flex justify-center md:justify-start">
                <BoardCard board={game.board}/>
            </div>
        </div>
    </div>
{:catch error}
    <p style="color: red">{error.message}</p>
{/await}