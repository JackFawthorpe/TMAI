import { api } from "./configs/axiosConfigs"
import { defineCancelApiObject } from "./configs/axiosUtils"

export const API = {
    getAllCards: async function (cancel = false) {
        const response = await api.request({
            url: `/cards`,
            method: "GET",
            signal: cancel ? cancelApiObject[this.get.name].handleRequestCancellation().signal : undefined
        })

        return response.data
    },
    getGame: async function (cancel = false) {
        const response = await api.request({
            url: `/game`,
            method: "GET",
            signal: cancel ? cancelApiObject[this.get.name].handleRequestCancellation().signal : undefined
        })

        return response.data
    },
    postGame: async function(playerNames: string[], cancel = false) {
        const response = await api.request({
            url: `/game`,
            method: "POST",
            data: playerNames,
            signal: cancel ? cancelApiObject[this.get.name].handleRequestCancellation().signal : undefined
        })

        return response.data
    },
    takePlayer: async function(playerId: number, cancel = false) {
        const response = await api.request({
            url: `/game/play`,
            method: "POST",
            data: playerId,
            headers: {"Content-Type": "application/json"},
            signal: cancel ? cancelApiObject[this.get.name].handleRequestCancellation().signal : undefined
        })
        return response.status === 200;
    }
}

const cancelApiObject = defineCancelApiObject(API)