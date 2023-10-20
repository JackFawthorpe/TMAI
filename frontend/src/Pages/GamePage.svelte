<script lang="ts">

    import Loader from "../Components/Layout/Loader.svelte";
    import {API} from "../apis/API";
    import {navigate} from "svelte-routing";
    import PlayerCard from "../Components/Game/PlayerCard.svelte";
    import BoardCard from "../Components/Game/BoardCard.svelte";
    import type {Game} from "../Types/Game";
    import {SocketAPI} from "../apis/configs/SocketAPI";
    import GlobalParametersCard from "../Components/Game/GlobalParametersCard.svelte";


    let game: Game;
    const loadGame = async () => {
        try {
            game = await API.getGame();
            SocketAPI.connect(true);

        } catch (e) {
            if (e.response.status === 404) {
                navigate("game/create")
            } else {
                throw e
            }
        }
    }

    const handleMessage = () => {
        SocketAPI.sendMessage({message:"Hyallo"});
    }

    const promise = loadGame();

</script>

{#await promise}
    <Loader/>
{:then _}
    <div class="container-fluid h-screen flex gap-2 pe-2">
        <div class="w-[40%] bg-white">
            <div class="grid grid-cols-1 xl:grid-cols-2">
                {#each game.players as player}
                    <PlayerCard player={player}/>
                {/each}
            </div>
        </div>
        <div class="w-[60%] m-auto flex flex-col items-center gap-2">
            <BoardCard board={game.board}/>
            <GlobalParametersCard params={game.globalParameters}/>
        </div>
    </div>
{:catch error}
    <p style="color: red">{error.message}</p>
{/await}