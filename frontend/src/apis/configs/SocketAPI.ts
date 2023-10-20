
import type {Turn} from "../../Types/Turn";
import {Client} from "@stomp/stompjs";
let stompClient;
let onMessage = (frame) => {console.log(frame)}

export const SocketAPI = {
    onConnect: () => {},
    onDisconnect: () => {},
    connect: (debug = false) => {
        stompClient = new Client({
            brokerURL: window.location.origin.replace("http", "ws") + "/socket/game/socket",
            onConnect: () => {
                stompClient.subscribe('/topic/game/turn', onMessage, {});
                SocketAPI.onConnect();
            },
            debug: function (str) {
                console.log(str);
            },
            onStompError: () => {
                console.log("Web Socket Error Occured");
                SocketAPI.onDisconnect();
            },
            onWebSocketError: () => {
                console.log("Web Socket Error Occured");
                SocketAPI.onDisconnect();
            },
            onDisconnect: () => {
                SocketAPI.onDisconnect();
            }
        })

        stompClient.activate();
    },
    disconnect: () => {stompClient.deactivate();},
    onMessage: onMessage,
    sendMessage: (turn:  Turn) => {stompClient.send('/app/game/turn', turn, {})}
}