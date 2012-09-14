package com.facebook.presto.block.rle;

import com.facebook.presto.Range;
import com.facebook.presto.TupleInfo;
import com.facebook.presto.block.TupleStream;
import com.facebook.presto.block.Cursor;

import java.util.Iterator;

import static com.google.common.base.Preconditions.checkNotNull;

public class RunLengthEncodedBlockStream
        implements TupleStream, Iterable<RunLengthEncodedBlock>
{
    private final TupleInfo tupleInfo;
    private final Iterable<RunLengthEncodedBlock> runLengthEncodedBlocks;

    public RunLengthEncodedBlockStream(TupleInfo tupleInfo, Iterable<RunLengthEncodedBlock> runLengthEncodedBlocks)
    {
        checkNotNull(tupleInfo, "tupleInfo is null");
        checkNotNull(runLengthEncodedBlocks, "runLengthEncodedBlocks is null");

        this.tupleInfo = tupleInfo;
        this.runLengthEncodedBlocks = runLengthEncodedBlocks;
    }

    @Override
    public TupleInfo getTupleInfo()
    {
        return tupleInfo;
    }

    @Override
    public Range getRange()
    {
        return Range.ALL;
    }

    @Override
    public Cursor cursor()
    {
        return new RunLengthEncodedCursor(tupleInfo, runLengthEncodedBlocks.iterator());
    }

    @Override
    public Iterator<RunLengthEncodedBlock> iterator()
    {
        return runLengthEncodedBlocks.iterator();
    }
}