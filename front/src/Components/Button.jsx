import { PlusIcon } from '@radix-ui/react-icons'
import React from 'react'

function Button({title,design,onclick}) {
  return (
    <button className={design} onClick={onclick}>
    
  <PlusIcon  className="w-4 h-4 mr-2" />
  {title}
    
</button>

  )
}

export default Button