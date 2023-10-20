<script lang="ts">

    import Loader from "../Components/Layout/Loader.svelte";
    import {API} from "../apis/API";
    import {navigate} from "svelte-routing";
    import BoardCard from "../Components/Game/BoardCard.svelte";
    import type {Game} from "../Types/Game";
    import {SocketAPI} from "../apis/configs/SocketAPI";
    import GlobalParametersCard from "../Components/Game/GlobalParametersCard.svelte";
    import SocketConnectionDisplay from "../Components/Game/SocketConnectionDisplay.svelte";
    import PlayerBrowser from "../Components/Game/PlayerBrowser.svelte";


    let game: Game;
    let connected: boolean;
    const loadGame = async () => {
        try {
            game = await API.getGame();
            SocketAPI.onConnect = () => {connected = true};
            SocketAPI.onDisconnect = () => {connected = false};
            SocketAPI.onMessage = (frame) => {
                game = JSON.parse(frame.body);
            }
            SocketAPI.connect(true);

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
    <div class="container-fluid h-screen flex gap-2 pe-2">
        <div class="w-[40%] bg-white flex flex-col justify-between">
            <PlayerBrowser players={game.players}/>
            <div>
                <SocketConnectionDisplay connected={connected}/>
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