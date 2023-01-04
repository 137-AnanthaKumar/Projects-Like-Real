import { useState } from 'react';
import Gallary from './components/Gallary';
import Header from './components/Header'
import ImageAdd from './components/ImageAdd';

function App() {
  const [count, setCount] = useState(0);
  function getCountValue(a){
    console.log(a);
    setCount(pre=>pre+a);
  }
  return (
    <div className="container">
      <Header />
      <ImageAdd addImage={getCountValue}/>
      <Gallary update={count}/>
    </div>
  );
}

export default App;
