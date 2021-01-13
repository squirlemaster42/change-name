import React, { useEffect, useState } from 'react'
import './App.css'
import {gameSubject, initGame, resetGame} from './Game'
import Board from './Board'
import socketClient from "socket.io-client"
const SERVER = "http://127.0.0.1:8080";

function App() {
  const [board, setBoard] = useState([])
  const [isGameOver, setIsGameOver] = useState()
  const [result, setResult] = useState()
  const [turn, setTurn] = useState()

  var socket = socketClient (SERVER);
  socket.on('connection', () => {
    console.log(`I'm connected with the back-end`);
  });

  useEffect(() => {
    initGame()
    const subscribe = gameSubject.subscribe((game) => {
      setBoard(game.board)
      setIsGameOver(game.isGameOver)
      setResult(game.result)
      setTurn(game.turn)
    })
    return () => subscribe.unsubscribe()
  }, [])

  return (
    <div className="container">
      {isGameOver && (
        <h2 className="vertical-text">
          GAME OVER
          <button onClick={resetGame}>
            <span className="vertical-text"> NEW GAME</span>
          </button>
        </h2>
      )}
      <div className="board-container">
        <Board board={board} turn={turn} />
      </div>
      {result && <p className="vertical-text">{result}</p>}
    </div>
  )
}

export default App
