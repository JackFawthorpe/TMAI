import webstomp from "webstomp-client";
import type {Turn} from "../../Types/Turn";

let stompClient;
let onConnect =  () => {console.log("Connected to Socket")};
let onFail = () => {console.log("Failed to connect to Socket")};

let onMessage = (frame) => {console.log(frame)}

export const SocketAPI = {
    onConnect: onConnect,
    onFail: onFail,
    connect: (debug = false) => {
        stompClient = webstomp.client(window.location.origin.replace("http", "ws") + "/socket/game/socket");
        stompClient.hasDebug = debug;
        stompClient.connect({}, () => {
            stompClient.subscribe('socket/topic/game', onMessage, {});
            onConnect();
        }, onFail);
    },
    disconnect: () => {
        stompClient ? stompClient.disconect() : console.log("Can only disconnect if previously connected");
    },
    onMessage: onMessage,
    sendMessage: (turn:  Turn) => {stompClient.send('/socket/app/game', turn, {})}
}