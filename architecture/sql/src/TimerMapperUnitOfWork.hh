/*
 * ----------------------------------------------------------------------------
 * (c) 2005-2023 - CROWN OWNED COPYRIGHT. All rights reserved.
 * The copyright of this Software is vested in the Crown
 * and the Software is the property of the Crown.
 * ----------------------------------------------------------------------------
 * SPDX-License-Identifier: Apache-2.0
 * ----------------------------------------------------------------------------
 */

#ifndef Sql_TimerMapperUnitOfWork_HH
#define Sql_TimerMapperUnitOfWork_HH

#include <set>

#include "sql/TimerMapper.hh"
#include "sql/UnitOfWorkObserver.hh"

namespace SQL {

    // ***********************************************************************
    //!
    // ***********************************************************************

    class TimerMapperUnitOfWork : public UnitOfWorkObserver {
      public:
        TimerMapperUnitOfWork(TimerMapper &parent);
        ~TimerMapperUnitOfWork();

        void createTimer(const uint32_t timerId);
        void deleteTimer(const uint32_t timerId);
        void updateTimer(const TimerMapper::EventTimerData &data);

      private:
        virtual void flush(UnitOfWorkContext &context);
        virtual void committed(UnitOfWorkContext &context);
        virtual void startTransaction(UnitOfWorkContext &context);
        virtual void commitTransaction(UnitOfWorkContext &context);
        virtual void abortTransaction(UnitOfWorkContext &context);

        void clear();
        bool isDirty();
        void primeForChanges();

      private:
        TimerMapper &parent;

        typedef std::set<uint32_t> IdSet;

        typedef std::map<uint32_t, TimerMapper::EventTimerData> UpdateSet;

        IdSet createSet;
        IdSet deleteSet;
        UpdateSet updateSet;
    };

} // end namespace SQL

#endif
