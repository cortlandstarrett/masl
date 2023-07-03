/*
 * ----------------------------------------------------------------------------
 * (c) 2005-2023 - CROWN OWNED COPYRIGHT. All rights reserved.
 * The copyright of this Software is vested in the Crown
 * and the Software is the property of the Crown.
 * ----------------------------------------------------------------------------
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ----------------------------------------------------------------------------
 * Classification: UK OFFICIAL
 * ----------------------------------------------------------------------------
 */

#ifndef TRANSIENT_PairFirstIterator_HH
#define TRANSIENT_PairFirstIterator_HH

#include "boost/operators.hpp"

namespace transient
{

  template<class InnerIterator>
  class PairFirstIterator : public std::iterator<std::input_iterator_tag, typename InnerIterator::value_type::first_type>, public boost::input_iteratable<PairFirstIterator<InnerIterator>,typename InnerIterator::value_type::first_type*>
  {
    public:
      PairFirstIterator ( const InnerIterator& pos ) : pos(pos) {}
      const typename InnerIterator::value_type::first_type& operator*() const { return pos->first; }
      PairFirstIterator& operator++()  { ++pos; return *this; }
      bool operator== ( const PairFirstIterator& rhs ) const { return pos == rhs.pos; }

    private:
      InnerIterator pos;
  };

}

#endif
