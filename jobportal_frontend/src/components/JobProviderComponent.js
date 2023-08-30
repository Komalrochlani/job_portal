import React,{useState} from 'react'

const Dropdown = ({ options, onSelect }) => {
    const [selectedOption, setSelectedOption] = useState(null);
  
    const handleOptionSelect = (option) => {
      setSelectedOption(option);
      onSelect(option);
    };
}

export default function JobProviderComponent() {
  return (
    <div>JobProviderComponent</div>
  )
}
